package dev.ionitaandreea.platformaconsiliere.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import dev.ionitaandreea.platformaconsiliere.enums.AppointmentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateRequest {

    @NotNull(message = "Id is required!")
    private Long id;

    private AppointmentType appointmentType;

    @NotNull
    private String specialization;

    @NotNull
    private Long doctorId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @NotNull
    private String location;


}
