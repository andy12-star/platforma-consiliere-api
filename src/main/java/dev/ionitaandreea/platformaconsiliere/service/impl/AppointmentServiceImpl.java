package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.request.AppointmentUpdateRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.AppointmentResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.AppointmentRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.AppointmentService;
import dev.ionitaandreea.platformaconsiliere.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;


    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
            appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<AppointmentResponse> getAllAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findAllByPatient_Id(patientId).stream()
                .map(Mapper::toAppointmentResponse).toList();
    }

    @Override
    public List<AppointmentResponse> getAllAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findAllByDoctor_Id(doctorId).stream()
                .map(Mapper::toAppointmentResponse).toList();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow();

    }

    @Override
    public AppointmentResponse updateAppointment(Long id, AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("appointment not found"));
        User doctor = userService.getUserById(appointmentUpdateRequest.getDoctorId());

        appointment.setAppointmentType(appointmentUpdateRequest.getAppointmentType());
        appointment.setSpecialization(appointmentUpdateRequest.getSpecialization());
        appointment.setDoctor(doctor);
        appointment.setDate(appointmentUpdateRequest.getDate());
        appointment.setLocation(appointmentUpdateRequest.getLocation());

        appointmentRepository.save(appointment);

        return Mapper.toAppointmentResponse(appointment);

    }


}

