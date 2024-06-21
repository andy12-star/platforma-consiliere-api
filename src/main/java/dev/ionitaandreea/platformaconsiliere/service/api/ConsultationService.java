package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.request.ConsultationUpdateRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.ConsultationResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Consultation;

import java.util.List;

public interface ConsultationService {

    Consultation saveConsultation(Consultation consultation);

    void deleteConsultation(Long consultationId);

    List<ConsultationResponse> getAllConsultationsByPatientId(Long patientId);

    List<ConsultationResponse> getAllConsultationsByDoctorId(Long doctorId);

    List<ConsultationResponse> getAllConsultationsForDoctorByPatient(Long doctorId, Long patientId);

    Consultation getConsultationByAppointmentId(Long appointmentId);

    ConsultationResponse updateConsultation(Long id, ConsultationUpdateRequest consultationUpdateRequest);
}
