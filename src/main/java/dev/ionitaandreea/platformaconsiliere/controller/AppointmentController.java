package dev.ionitaandreea.platformaconsiliere.controller;


import dev.ionitaandreea.platformaconsiliere.dto.request.AppointmentRequest;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.service.api.AppointmentService;
import dev.ionitaandreea.platformaconsiliere.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read') or hasAuthority('medic:read')")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserServiceImpl userServiceImpl;

    @PostMapping()
    public ResponseEntity<?> saveAppointment(@RequestBody AppointmentRequest appointmentRequest){
        User apptPatientUser = userServiceImpl.getUserById(appointmentRequest.getPatientId());
        User apptDoctorUser = userServiceImpl.getUserById(appointmentRequest.getDoctorId());
        appointmentService.saveAppointment(Mapper.toAppointment(appointmentRequest,apptPatientUser,apptDoctorUser));
        return ResponseEntity.ok("Appointment saved successfully");
    }

    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.status(200).body("Appointment deleted successfully!");
    }

    @GetMapping({"/patient/{patientId}"})
    public ResponseEntity<?> getAllAppointmentsForPatient(@PathVariable Long patientId){
        return  ResponseEntity.ok(appointmentService.getAllAppointmentsByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAllAppointmentsForDoctor(@PathVariable Long doctorId){
        return ResponseEntity.ok(appointmentService.getAllAppointmentsByDoctorId(doctorId));
    }

}
