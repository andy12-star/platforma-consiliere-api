package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.request.AuthenticationRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.RefreshTokenRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request);

    void logout(String username);
}
