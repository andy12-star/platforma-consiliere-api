package dev.ionitaandreea.platformaconsiliere.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationResponse {

    private Long id;

    private int duration;

    private String observations;

    private String recommendations;

    private String doctorName;

    private String patientName;

    private String specialization;

    private LocalDateTime date;

}

