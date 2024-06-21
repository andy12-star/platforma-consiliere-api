package dev.ionitaandreea.platformaconsiliere.dto.response;

import dev.ionitaandreea.platformaconsiliere.enums.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {

    private Long id;

    private String location;

    private String specialization;

    private LocalDateTime date;

    private UserResponse doctor;

    private UserResponse patient;

    private AppointmentType appointmentType;

}
