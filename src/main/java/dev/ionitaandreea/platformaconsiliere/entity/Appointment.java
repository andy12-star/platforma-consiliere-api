package dev.ionitaandreea.platformaconsiliere.entity;


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

