package dev.ionitaandreea.platformaconsiliere.repository;

import dev.ionitaandreea.platformaconsiliere.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    List<Consultation> findAllByAppointment_Patient_Id(Long patientId);
    List<Consultation> findAllByAppointment_Doctor_Id(Long doctorId);

    List<Consultation> findAllByAppointment_Doctor_IdAndAppointment_Patient_Id(Long doctorId, Long patientId);

    Optional<Consultation> findByAppointment_Id(Long appointmentId);
}

