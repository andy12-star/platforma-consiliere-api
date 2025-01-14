package dev.ionitaandreea.platformaconsiliere.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String accessToken;

    private String tokenType;

    private Integer expiresIn;

    private String refreshToken;

    private UserResponse user;
}
