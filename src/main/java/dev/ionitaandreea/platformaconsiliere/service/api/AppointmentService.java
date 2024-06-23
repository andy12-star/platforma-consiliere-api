package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.request.AppointmentUpdateRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.AppointmentResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    void saveAppointment(Appointment appointment);

    void deleteAppointment(Long appointmentId);

    List<AppointmentResponse> getAllAppointmentsByPatientId(Long patientId);

    List<AppointmentResponse> getAllAppointmentsByDoctorId(Long doctorId);

    Appointment getAppointmentById(Long id);

    AppointmentResponse updateAppointment(Long id, AppointmentUpdateRequest appointmentUpdateRequest);
}
