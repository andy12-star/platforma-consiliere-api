package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;
import dev.ionitaandreea.platformaconsiliere.entity.User;

import java.util.Set;

public interface DoctorService {

    Set<UserResponse> getAllDoctors();
}
