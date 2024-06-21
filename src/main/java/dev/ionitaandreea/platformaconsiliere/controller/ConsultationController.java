package dev.ionitaandreea.platformaconsiliere.controller;


import dev.ionitaandreea.platformaconsiliere.common.ResponseObject;
import dev.ionitaandreea.platformaconsiliere.dto.request.ConsultationRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.ConsultationUpdateRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.ConsultationResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.service.api.ConsultationService;
import dev.ionitaandreea.platformaconsiliere.service.impl.AppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consultation")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read') or hasAuthority('medic:read')")
public class ConsultationController {

    private static final Logger log = LoggerFactory.getLogger(ConsultationController.class);
    private final ConsultationService consultationService;
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

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity <?> getAllConsultationByDoctor(@PathVariable Long doctorId){
        return ResponseEntity.ok(consultationService.getAllConsultationsByDoctorId(doctorId));
    }

    @GetMapping("/doctor-patient/{doctorId}/{patientId}")
    public ResponseEntity<?> getAllConsultationsForDoctorByPatient(@PathVariable Long doctorId, @PathVariable Long patientId) {
        return ResponseEntity.ok(consultationService.getAllConsultationsForDoctorByPatient(doctorId, patientId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity <?> getAllConsultationByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(consultationService.getAllConsultationsByPatientId(patientId));
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<?> getConsultationByAppointmentId(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(consultationService.getConsultationByAppointmentId(appointmentId));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseObject<ConsultationResponse> updateConsultation(@PathVariable Long id, @RequestBody ConsultationUpdateRequest consultationUpdateRequest) {
        log.info("Received consultation update request for consultation with id {}", id);

        return ResponseObject.<ConsultationResponse>builder()
                .status(ResponseObject.ResponseStatus.SUCCESSFUL)
                .message("Cnsultation updated successfully")
                .data(consultationService.updateConsultation(id, consultationUpdateRequest))
                .build();
    }

}
