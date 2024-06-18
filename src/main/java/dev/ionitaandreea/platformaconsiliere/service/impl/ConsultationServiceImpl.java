package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.response.ConsultationResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Consultation;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.ConsultationRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long consultationId) {
        consultationRepository.deleteById(consultationId);
    }

    @Override
    public List<ConsultationResponse> getAllConsultationsByPatientId(Long patientId) {
        return consultationRepository.findAllByAppointment_Patient_Id(patientId).stream()
                .map(Mapper::toConsultationResponse).toList();
    }

    @Override
    public List<ConsultationResponse> getAllConsultationsByDoctorId(Long doctorId) {
        return consultationRepository.findAllByAppointment_Doctor_Id(doctorId).stream()
                .map(Mapper::toConsultationResponse).toList();
    }


}
