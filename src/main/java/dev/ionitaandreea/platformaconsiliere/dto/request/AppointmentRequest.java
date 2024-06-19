package dev.ionitaandreea.platformaconsiliere.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.ionitaandreea.platformaconsiliere.entity.Consultation;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.enums.AppointmentType;
import jakarta.persistence.*;
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
public class AppointmentRequest {


    private Long id;

    @NotNull(message="PatientId is required")
    private Long patientId;

    @NotNull(message="DoctorId is required")
    private Long doctorId;

   @NotNull(message = "Appointment type is required")
   private AppointmentType appointmentType;

    @NotNull(message="Specialization is required")
    private String specialization;

    @NotNull(message = "date is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private String location;
}
