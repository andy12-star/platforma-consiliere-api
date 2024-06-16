package dev.ionitaandreea.platformaconsiliere.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ManyToOne
    private User patient;

    @ManyToOne
    private User doctor;

    private String services;

    private int duration;

    private String observations;

    private String recommendations;

}
