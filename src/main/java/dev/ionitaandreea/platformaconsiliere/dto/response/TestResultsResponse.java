package dev.ionitaandreea.platformaconsiliere.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResultsResponse {

    private Long id;

    private Long patientId;

    private Double personality;

    private Double smi;

    private Double ysqEmotionalPrivacy;

    private Double ysqInstability;

    private Double ysqDoubt;
}
