package dev.ionitaandreea.platformaconsiliere.repository;

import dev.ionitaandreea.platformaconsiliere.entity.Role;
import dev.ionitaandreea.platformaconsiliere.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
