package dev.ionitaandreea.platformaconsiliere.dto.request;

import dev.ionitaandreea.platformaconsiliere.entity.Consultation;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.enums.AppointmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {

 @NotNull(message="Id is required")
    private Long id;

    @NotNull(message="PatientId is required")
    private Long patientId;

    @NotNull(message = "Appointment type is required")
    private AppointmentType appointmentType;

    @NotNull(message="DoctorId is required")
    private Long doctorId;

    @NotNull(message="Specialization is required")
    private String specialization;

    @NotNull(message="DoctorFirstName is required")
    private String doctorFirstName;

    @NotNull(message="DoctorLastName is required")
    private String doctorLastName;

    @NotNull(message = "date is required")
    private LocalDateTime date;

    private String location;

    @OneToOne
    private Consultation consultation;

    @NotNull(message = "Hour is required")
    private int hour;



}
