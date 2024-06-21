package dev.ionitaandreea.platformaconsiliere.controller;


import dev.ionitaandreea.platformaconsiliere.common.ResponseObject;
import dev.ionitaandreea.platformaconsiliere.dto.request.AppointmentRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.AppointmentUpdateRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.AppointmentResponse;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.service.api.AppointmentService;
import dev.ionitaandreea.platformaconsiliere.service.api.ConsultationService;
import dev.ionitaandreea.platformaconsiliere.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read') or hasAuthority('doctor:read')")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;
    private final ConsultationService consultationService;

    @PostMapping()
    public ResponseEntity<?> saveAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        User apptPatientUser = userService.getUserById(appointmentRequest.getPatientId());
        User apptDoctorUser = userService.getUserById(appointmentRequest.getDoctorId());
        appointmentService.saveAppointment(Mapper.toAppointment(appointmentRequest, apptPatientUser, apptDoctorUser));
        return ResponseEntity.ok("Appointment saved successfully");
    }

    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.status(200).body("Appointment deleted successfully!");
    }

    @GetMapping({"/patient/{patientId}"})
    public ResponseEntity<?> getAllAppointmentsForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAllAppointmentsForDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsByDoctorId(doctorId));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseObject<AppointmentResponse> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {

        log.info("Received appointment update request for appointment id:{}", id);

        return ResponseObject.<AppointmentResponse>builder()
                .status(ResponseObject.ResponseStatus.SUCCESSFUL)
                .message("Apppointment updated successfully")
                .data(appointmentService.updateAppointment(id, appointmentUpdateRequest))
                .build();
    }


}
