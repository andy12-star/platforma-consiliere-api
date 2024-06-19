package dev.ionitaandreea.platformaconsiliere.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRequest {


    private Long id;

    @NotNull(message="Duration is required")
    private int duration;

    @NotNull(message="Observations are required")
    private String observations;

    @NotNull(message="Recommandations are required")
    private String recommendations;

    @NotNull(message="AppointmentId are required")
    private Long AppointmentId;
}
