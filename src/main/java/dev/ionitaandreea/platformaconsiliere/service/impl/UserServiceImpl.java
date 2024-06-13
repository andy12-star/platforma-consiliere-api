package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.request.UserRegistrationRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Role;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.UserRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.RoleService;
import dev.ionitaandreea.platformaconsiliere.service.api.UserService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerUser(UserRegistrationRequest userRegistrationRequest) {

        if (userRepository.existsByUsername(userRegistrationRequest.getEmail()))
            throw new EntityExistsException(String.format("Email %s already exists", userRegistrationRequest.getEmail()));

        Role role = roleService.getRoleByUserType(userRegistrationRequest.getType());

        User user = User.builder()
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .username(userRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .phoneNumber(userRegistrationRequest.getPhoneNumber())
                .roles(Collections.singleton(role))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .verified(true)
                .build();

        return Mapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {

        return userRepository.findAll(pageable).map(Mapper::toUserResponse);
    }
}
