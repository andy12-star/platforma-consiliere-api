package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;

import java.util.Set;

public interface DoctorService {

    Set<UserResponse> getAllDoctors();

    Set<UserResponse> getAllDoctorsForPatient(Long patientId);
}
