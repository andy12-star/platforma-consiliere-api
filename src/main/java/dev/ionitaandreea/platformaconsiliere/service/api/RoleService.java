package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.entity.Role;
import dev.ionitaandreea.platformaconsiliere.enums.UserType;

public interface RoleService {

    Role getRoleByUserType(UserType userType);
}
