package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import dev.ionitaandreea.platformaconsiliere.enums.RoleName;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.AppointmentRepository;
import dev.ionitaandreea.platformaconsiliere.repository.UserRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.PatientService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public PatientServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<UserResponse> getAllPatientsForDoctor(Long doctorId) {
        return appointmentRepository.findAllByDoctor_Id(doctorId).stream()
                .map(Appointment::getPatient)
                .map(Mapper::toUserResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserResponse> getAllPatients() {
        return userRepository.findAllByRoles(RoleName.ROLE_USER.getValue()).stream()
                .map(Mapper::toUserResponse).collect(Collectors.toSet());
    }
}
