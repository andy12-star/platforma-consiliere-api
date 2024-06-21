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
public class ConsultationUpdateRequest {

    @NotNull
    private Long id;


    private int duration;


    private String observations;


    private String recommendations;


}

