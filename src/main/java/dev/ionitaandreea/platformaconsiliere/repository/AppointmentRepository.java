package dev.ionitaandreea.platformaconsiliere.repository;

import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> findAllByPatient_Id(Long patientId);

    List<Appointment> findAllByDoctor_Id(Long doctorId);

}
