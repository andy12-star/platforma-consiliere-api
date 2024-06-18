package dev.ionitaandreea.platformaconsiliere.controller;


import dev.ionitaandreea.platformaconsiliere.dto.request.ConsultationRequest;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.service.api.ConsultationService;
import dev.ionitaandreea.platformaconsiliere.service.impl.AppointmentServiceImpl;
import dev.ionitaandreea.platformaconsiliere.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consultation")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read') or hasAuthority('medic:read')")
public class ConsultationController {

    private final ConsultationService consultationService;
    private final UserServiceImpl userServiceImpl;
    private final AppointmentServiceImpl appointmentServiceImpl;

    @PostMapping
    public ResponseEntity<?> saveConsultation(@RequestBody ConsultationRequest consultationRequest){
        Appointment appointment= appointmentServiceImpl.getAppointmentById(consultationRequest.getAppointmentId());
        consultationService.saveConsultation(Mapper.toConsultation(consultationRequest,appointment));
        return ResponseEntity.ok("Consultation saved successfully");
    }

    @DeleteMapping("{consultationId}")
    public ResponseEntity<?> deleteConsultation(@PathVariable Long consultationId){
        consultationService.deleteConsultation(consultationId);
        return ResponseEntity.status(200).body("Consultation deteled successfully");
    }

}
