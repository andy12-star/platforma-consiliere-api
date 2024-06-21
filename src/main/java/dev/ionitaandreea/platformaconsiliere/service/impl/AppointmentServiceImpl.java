package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.request.AppointmentUpdateRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.AppointmentResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import dev.ionitaandreea.platformaconsiliere.entity.Consultation;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.enums.AppointmentType;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.AppointmentRepository;
import dev.ionitaandreea.platformaconsiliere.repository.ConsultationRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.AppointmentService;
import dev.ionitaandreea.platformaconsiliere.service.api.ConsultationService;
import dev.ionitaandreea.platformaconsiliere.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final ConsultationService consultationService;
    private final ConsultationRepository consultationRepository;


    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<AppointmentResponse> getAllAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findAllByPatient_Id(patientId).stream()
                .map(Mapper::toAppointmentResponse).toList();
    }

    @Override
    public List<AppointmentResponse> getAllAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findAllByDoctor_Id(doctorId).stream()
                .map(Mapper::toAppointmentResponse).toList();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow();

    }

    @Override
    public AppointmentResponse updateAppointment(Long id, AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("appointment not found"));
        User doctor = userService.getUserById(appointmentUpdateRequest.getDoctorId());

        if (appointmentTypeIsChangedToHonored(appointment, appointmentUpdateRequest)) {
            Optional<Consultation> optApptConsultation = consultationRepository.findByAppointment_Id(appointment.getId());
            if (optApptConsultation.isEmpty()) {
                createConsultationForAppointment(appointment);
            }
        } else if (appointmentTypeIsChangedFromHonored(appointment, appointmentUpdateRequest)) {
            deleteConsultationForAppointment(appointment);
        }

        updateAppointmentData(appointmentUpdateRequest, appointment, doctor);

        appointmentRepository.save(appointment);

        return Mapper.toAppointmentResponse(appointment);

    }

    private void updateAppointmentData(AppointmentUpdateRequest appointmentUpdateRequest, Appointment appointment, User doctor) {
        appointment.setAppointmentType(appointmentUpdateRequest.getAppointmentType());
        appointment.setSpecialization(appointmentUpdateRequest.getSpecialization());
        appointment.setDoctor(doctor);
        appointment.setDate(appointmentUpdateRequest.getDate());
        appointment.setLocation(appointmentUpdateRequest.getLocation());
    }

    private boolean appointmentTypeIsChangedToHonored(Appointment appointment, AppointmentUpdateRequest appointmentUpdateRequest) {
        return !appointment.getAppointmentType().equals(AppointmentType.HONORED)
               && appointmentUpdateRequest.getAppointmentType().equals(AppointmentType.HONORED);
    }

    private boolean appointmentTypeIsChangedFromHonored(Appointment appointment, AppointmentUpdateRequest appointmentUpdateRequest) {
        return appointment.getAppointmentType().equals(AppointmentType.HONORED)
               && !appointmentUpdateRequest.getAppointmentType().equals(AppointmentType.HONORED);
    }

    private void createConsultationForAppointment(Appointment appointment) {
        Consultation consultation = Consultation.builder()
                .appointment(appointment)
                .build();
        consultationService.saveConsultation(consultation);
        log.info("Consultation created for appointment with id {}", appointment.getId());
    }

    private void deleteConsultationForAppointment(Appointment appointment) {
        Consultation consultation = consultationService.getConsultationByAppointmentId(appointment.getId());
        consultationService.deleteConsultation(consultation.getId());
        log.info("Consultation deleted for appointment with id {}", appointment.getId());
    }


}

