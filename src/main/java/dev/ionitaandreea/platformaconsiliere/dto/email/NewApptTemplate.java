package dev.ionitaandreea.platformaconsiliere.dto.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewApptTemplate {

    private String doctorName;
    private String patientName;
    private String date;
    private String time;
    private String location;
    private String specialization;
}
