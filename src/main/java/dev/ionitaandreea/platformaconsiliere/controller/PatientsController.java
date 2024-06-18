package dev.ionitaandreea.platformaconsiliere.controller;

import dev.ionitaandreea.platformaconsiliere.service.api.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read') or hasAuthority('medic:read')")
public class PatientsController {

    private final PatientService patientService;

    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getAllPatientsByDoctor(@PathVariable Long doctorId){
        return ResponseEntity.ok(patientService.getAllPatientsForDoctor(doctorId));
    }
}
