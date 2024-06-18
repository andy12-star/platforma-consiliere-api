package dev.ionitaandreea.platformaconsiliere.repository;

import dev.ionitaandreea.platformaconsiliere.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    List<Consultation> findAllByAppointment_Patient_Id(Long patientId);
    List<Consultation> findAllByAppointment_Doctor_Id(Long doctorId);
}

