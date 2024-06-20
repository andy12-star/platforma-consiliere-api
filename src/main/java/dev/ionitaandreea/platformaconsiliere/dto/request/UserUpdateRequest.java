package dev.ionitaandreea.platformaconsiliere.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotNull(message = "Id is required!")
    private Long id;
    @NotNull(message = "First Name is required!")
    private String firstName;
    @NotNull(message = "Last Name is required!")
    private String lastName;
    @NotNull(message = "Date of birth is required!")
    private LocalDate dateOfBirth;
    @NotNull(message = "Username is required!")
    private String username;
    @NotNull(message = "Phone Number is required!")
    private String phoneNumber;
    @NotNull(message = "Faculty is required!")
    private String faculty;
}
