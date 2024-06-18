package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.AppointmentRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.PatientService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final AppointmentRepository appointmentRepository;

    public PatientServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Set<UserResponse> getAllPatientsForDoctor(Long doctorId) {
        return appointmentRepository.findAllByDoctor_Id(doctorId).stream()
                .map(Appointment::getPatient)
                .map(Mapper::toUserResponse)
                .collect(Collectors.toSet());
    }
}
