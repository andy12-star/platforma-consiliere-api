package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.request.UserRegistrationRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest userRegistrationRequest);

    Page<UserResponse> getAllUsers(Pageable pageable);
}
