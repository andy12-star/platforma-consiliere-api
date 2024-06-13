package dev.ionitaandrea.platformaconsiliere.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.ionitaandrea.platformaconsiliere.util.TestUtil;
import dev.ionitaandreea.platformaconsiliere.PlatformaConsiliereApplication;
import dev.ionitaandreea.platformaconsiliere.auth.CustomUserDetailsService;
import dev.ionitaandreea.platformaconsiliere.common.ResponseObject;
import dev.ionitaandreea.platformaconsiliere.controller.UserController;
import dev.ionitaandreea.platformaconsiliere.dto.request.UserRegistrationRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;
import dev.ionitaandreea.platformaconsiliere.service.api.JwtService;
import dev.ionitaandreea.platformaconsiliere.service.api.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ContextConfiguration(classes = {PlatformaConsiliereApplication.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    private final TestUtil testUtil = new TestUtil();

    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Test
    void testUserRegistration() throws Exception {
        UserResponse response = testUtil.getUserResponse();
        UserRegistrationRequest request = testUtil.getUserRegistrationRequest();

        when(userService.registerUser(any(UserRegistrationRequest.class))).thenReturn(response);

        this.mockMvc.perform(post("/api/v1/users")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(ResponseObject.ResponseStatus.SUCCESSFUL.getValue()))
                .andExpect(jsonPath("$.message").value("User registered successfully"))
                .andExpect(jsonPath("$.data.id").value(response.getId()))
                .andExpect(jsonPath("$.data.firstName").value(response.getFirstName()))
                .andExpect(jsonPath("$.data.lastName").value(response.getLastName()))
                .andExpect(jsonPath("$.data.username").value(response.getUsername()))
                .andExpect(jsonPath("$.data.phoneNumber").value(response.getPhoneNumber()))
                .andExpect(jsonPath("$.data.verified").value(response.isVerified()))
                .andExpect(jsonPath("$.data.roles[0].name").value(response.getRoles().get(0).getName().getValue()));
    }

    @Test
    void testUserRegistration_whenRequestHasEmailAsNull() throws Exception {
        UserRegistrationRequest request = testUtil.getUserRegistrationRequest();
        request.setEmail(null);

        this.mockMvc.perform(post("/api/v1/users")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(ResponseObject.ResponseStatus.FAILED.getValue()))
                .andExpect(jsonPath("$.message").value("Email is required"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    void testGetAllUsers() throws Exception {
        UserResponse response = testUtil.getUserResponse();

        when(userService.getAllUsers(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(response)));

        this.mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(ResponseObject.ResponseStatus.SUCCESSFUL.getValue()))
                .andExpect(jsonPath("$.data.content[0].id").value(response.getId()))
                .andExpect(jsonPath("$.data.content[0].firstName").value(response.getFirstName()))
                .andExpect(jsonPath("$.data.content[0].lastName").value(response.getLastName()))
                .andExpect(jsonPath("$.data.content[0].username").value(response.getUsername()))
                .andExpect(jsonPath("$.data.content[0].phoneNumber").value(response.getPhoneNumber()))
                .andExpect(jsonPath("$.data.content[0].verified").value(response.isVerified()))
                .andExpect(jsonPath("$.data.content[0].roles[0].name").value(response.getRoles().get(0).getName().getValue()));
    }

}
