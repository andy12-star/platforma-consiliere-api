package dev.ionitaandrea.platformaconsiliere.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.ionitaandrea.platformaconsiliere.annotation.WithMockAdmin;
import dev.ionitaandrea.platformaconsiliere.util.TestUtil;
import dev.ionitaandreea.platformaconsiliere.PlatformaConsiliereApplication;
import dev.ionitaandreea.platformaconsiliere.auth.CustomUserDetailsService;
import dev.ionitaandreea.platformaconsiliere.common.ResponseObject;
import dev.ionitaandreea.platformaconsiliere.config.SecurityConfig;
import dev.ionitaandreea.platformaconsiliere.controller.AuthenticationController;
import dev.ionitaandreea.platformaconsiliere.dto.request.AuthenticationRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.RefreshTokenRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.AuthenticationResponse;
import dev.ionitaandreea.platformaconsiliere.service.api.AuthenticationService;
import dev.ionitaandreea.platformaconsiliere.service.api.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(AuthenticationController.class)
@ContextConfiguration(classes = {PlatformaConsiliereApplication.class})
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    private final TestUtil testUtil = new TestUtil();

    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Test
    void testLogin() throws Exception {
        AuthenticationResponse response = testUtil.getAuthenticationResponse();
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("john.doe@starter.com")
                .password("password")
                .build();

        when(authenticationService.login(any(AuthenticationRequest.class))).thenReturn(response);

        this.mockMvc.perform(post("/api/v1/auth/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(response.getAccessToken()))
                .andExpect(jsonPath("$.refreshToken").value(response.getRefreshToken()))
                .andExpect(jsonPath("$.tokenType").value(response.getTokenType()))
                .andExpect(jsonPath("$.expiresIn").value(response.getExpiresIn()));
    }

    @Test
    void testRefreshToken() throws Exception {
        AuthenticationResponse response = testUtil.getAuthenticationResponse();
        RefreshTokenRequest request = RefreshTokenRequest.builder()
                .refreshToken("sMUpGdeBv3De6m/WptP4iniOGmASD2qnFbW+aAosDUFq1yCEdCfc0z7EPQIDAQAB")
                .build();

        when(authenticationService.refreshToken(any(RefreshTokenRequest.class))).thenReturn(response);

        this.mockMvc.perform(post("/api/v1/auth/refresh")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(response.getAccessToken()))
                .andExpect(jsonPath("$.refreshToken").value(response.getRefreshToken()))
                .andExpect(jsonPath("$.tokenType").value(response.getTokenType()))
                .andExpect(jsonPath("$.expiresIn").value(response.getExpiresIn()));
    }

    @Test
    @WithMockAdmin
    void testLogout() throws Exception {

        this.mockMvc.perform(post("/api/v1/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(ResponseObject.ResponseStatus.SUCCESSFUL.getValue()))
                .andExpect(jsonPath("$.message").value("User logged out successfully"));
    }
}