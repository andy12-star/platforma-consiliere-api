package dev.ionitaandreea.platformaconsiliere.controller;


import dev.ionitaandreea.platformaconsiliere.service.api.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read')")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping()
    public ResponseEntity<?> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getAllDoctorsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(doctorService.getAllDoctorsForPatient(patientId));
    }
}
