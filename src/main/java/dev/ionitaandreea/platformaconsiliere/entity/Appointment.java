package dev.ionitaandreea.platformaconsiliere.entity;


import dev.ionitaandreea.platformaconsiliere.enums.AppointmentType;
import dev.ionitaandreea.platformaconsiliere.enums.TokenType;
import jakarta.persistence.*;
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
@Table(name = "appointemnts")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User patient;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Appointemt type is required")
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

    @OneToOne
    private Consultation consultation;

    @NotNull
    private int hour;

}

