package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;

import java.util.Set;

public interface PatientService {

    Set<UserResponse> getAllPatientsForDoctor(Long doctorId);

    Set<UserResponse> getAllPatients();
}
