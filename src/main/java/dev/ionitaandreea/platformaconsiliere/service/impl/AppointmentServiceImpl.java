package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.response.AppointmentResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.AppointmentRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;


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


}

