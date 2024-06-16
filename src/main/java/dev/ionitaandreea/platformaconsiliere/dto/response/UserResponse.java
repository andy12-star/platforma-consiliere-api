package dev.ionitaandreea.platformaconsiliere.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String username;

    private String faculty;

    private LocalDate dateOfBirth;


    private boolean verified;

    private List<RoleResponse> roles;
}
