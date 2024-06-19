package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.entity.Role;
import dev.ionitaandreea.platformaconsiliere.enums.RoleName;
import dev.ionitaandreea.platformaconsiliere.enums.UserType;
import dev.ionitaandreea.platformaconsiliere.repository.RoleRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByUserType(UserType userType) {
        RoleName roleName = switch (userType) {
            case USER -> RoleName.ROLE_USER;
            case DOCTOR -> RoleName.ROLE_DOCTOR;
            case ADMIN -> RoleName.ROLE_ADMIN;
        };

        return roleRepository.findByName(roleName).orElseThrow(() -> new
                EntityNotFoundException("Role not found"));
    }
}
