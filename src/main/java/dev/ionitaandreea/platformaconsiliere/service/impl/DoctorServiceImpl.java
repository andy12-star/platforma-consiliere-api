package dev.ionitaandreea.platformaconsiliere.service.impl;


import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Role;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.enums.RoleName;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.UserRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {


    private final UserRepository userRepository;

    @Override
    public Set<UserResponse> getAllDoctors() {
        return userRepository.findAllByRoles(RoleName.ROLE_DOCTOR.getValue()).stream()
                .map(Mapper::toUserResponse).collect(Collectors.toSet());
    }
}