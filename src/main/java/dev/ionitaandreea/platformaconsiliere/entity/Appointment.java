package dev.ionitaandreea.platformaconsiliere.entity;


import dev.ionitaandreea.platformaconsiliere.enums.AppointmentType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User patient;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Appointment type is required")
    private AppointmentType appointmentType;

    @NotNull
    private String specialization;

    @NotNull
    @ManyToOne
    private User doctor;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private String location;

}

