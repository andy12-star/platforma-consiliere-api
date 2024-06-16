package dev.ionitaandreea.platformaconsiliere.repository;

import dev.ionitaandreea.platformaconsiliere.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
