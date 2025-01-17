package dev.ionitaandreea.platformaconsiliere.mapper;

import dev.ionitaandreea.platformaconsiliere.dto.CustomUserDetails;
import dev.ionitaandreea.platformaconsiliere.dto.email.NewApptTemplate;
import dev.ionitaandreea.platformaconsiliere.dto.request.AppointmentRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.ConsultationRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.NotesRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.TestResultsRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.AppointmentResponse;
import dev.ionitaandreea.platformaconsiliere.dto.response.ConsultationResponse;
import dev.ionitaandreea.platformaconsiliere.dto.response.NotesResponse;
import dev.ionitaandreea.platformaconsiliere.dto.response.RoleResponse;
import dev.ionitaandreea.platformaconsiliere.dto.response.TestResultsResponse;
import dev.ionitaandreea.platformaconsiliere.dto.response.UserResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Appointment;
import dev.ionitaandreea.platformaconsiliere.entity.Consultation;
import dev.ionitaandreea.platformaconsiliere.entity.Notes;
import dev.ionitaandreea.platformaconsiliere.entity.TestResults;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static CustomUserDetails toCustomUserDetails(User user) {

        CustomUserDetails customUserDetails = new CustomUserDetails();
        BeanUtils.copyProperties(user, customUserDetails);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getName().getValue()));

            r.getPermissions().forEach(p -> {
                if (user.isVerified()) {
                    authorities.add(new SimpleGrantedAuthority(p.getName()));
                } else if (!p.isRequiresVerification()) {
                    authorities.add(new SimpleGrantedAuthority(p.getName()));
                }
            });
        });

        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse, "roles");

        List<RoleResponse> roles = new ArrayList<>();

        user.getRoles().forEach(role -> {
            RoleResponse roleResponse = new RoleResponse();
            BeanUtils.copyProperties(role, roleResponse, "permissions");

            List<String> permissions = new ArrayList<>();
            role.getPermissions().forEach(p -> permissions.add(p.getName()));
            roleResponse.setPermissions(permissions);
            roles.add(roleResponse);
        });

        userResponse.setRoles(roles);
        return userResponse;
    }

    public static Notes toNotes(NotesRequest notesRequest, User user) {
        return Notes.builder()
            .id(notesRequest.getId())
            .title(notesRequest.getTitle())
            .notes(notesRequest.getNotes())
            .user(user)
            .createdAt(LocalDateTime.now())
            .build();
    }

    public static NotesResponse toNotesResponse(Notes notes) {
        return NotesResponse.builder()
            .id(notes.getId())
            .title(notes.getTitle())
            .notes(notes.getNotes())
            .createdAt(notes.getCreatedAt())
            .build();
    }

    public static Appointment toAppointment(AppointmentRequest appointmentRequest, User patient, User doctor) {
        return Appointment.builder()
            .id(appointmentRequest.getId())
            .appointmentType(appointmentRequest.getAppointmentType())
            .specialization(appointmentRequest.getSpecialization())
            .date(appointmentRequest.getDate())
            .patient(patient)
            .doctor(doctor)
            .location(appointmentRequest.getLocation())
            .build();
    }


    public static AppointmentResponse toAppointmentResponse(Appointment appointment) {
        return AppointmentResponse.builder()
            .id(appointment.getId())
            .location(appointment.getLocation())
            .specialization(appointment.getSpecialization())
            .date(appointment.getDate())
            .doctor(toUserResponse(appointment.getDoctor()))
            .patient(toUserResponse(appointment.getPatient()))
            .appointmentType(appointment.getAppointmentType())
            .build();
    }

    public static Consultation toConsultation(ConsultationRequest consultationRequest, Appointment appointment) {
        return Consultation.builder()
            .id(consultationRequest.getId())
            .recommendations(consultationRequest.getRecommendations())
            .observations(consultationRequest.getObservations())
            .duration(consultationRequest.getDuration())
            .appointment(appointment)
            .build();
    }

    public static ConsultationResponse toConsultationResponse(Consultation consultation) {
        String doctorName = consultation.getAppointment().getDoctor().getFirstName()
            + " " + consultation.getAppointment().getDoctor().getLastName();
        String patientName = consultation.getAppointment().getPatient().getFirstName()
            + " " + consultation.getAppointment().getPatient().getLastName();
        return ConsultationResponse.builder()
            .id(consultation.getId())
            .recommendations(consultation.getRecommendations())
            .observations(consultation.getObservations())
            .duration(consultation.getDuration())
            .doctorName(doctorName)
            .patientName(patientName)
            .date(consultation.getAppointment().getDate())
            .specialization(consultation.getAppointment().getSpecialization())
            .appointmentType(consultation.getAppointment().getAppointmentType())
            .build();
    }

    public static NewApptTemplate toNewApptTemplate(Appointment appointment) {
        return NewApptTemplate.builder()
            .doctorName(appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName())
            .patientName(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName())
            .date(appointment.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
            .time(appointment.getDate().format(DateTimeFormatter.ISO_LOCAL_TIME))
            .location(appointment.getLocation())
            .specialization(appointment.getSpecialization())
            .build();
    }

    public static TestResultsResponse toResultsResponse(TestResults results) {
        return TestResultsResponse.builder()
            .id(results.getId())
            .patientId(results.getPatient().getId())
            .personality(results.getPersonality())
            .smi(results.getSmi())
            .ysqEmotionalPrivacy(results.getYsqEmotionalPrivacy())
            .ysqDoubt(results.getYsqDoubt())
            .ysqInstability(results.getYsqInstability())
            .build();
    }

    public static TestResults toResults(TestResultsRequest testResultsRequest, User patient) {
        return TestResults.builder()
            .id(testResultsRequest.getId())
            .patient(patient)
            .personality(testResultsRequest.getPersonality())
            .smi(testResultsRequest.getSmi())
            .ysqEmotionalPrivacy(testResultsRequest.getYsqEmotionalPrivacy())
            .ysqDoubt(testResultsRequest.getYsqDoubt())
            .ysqInstability(testResultsRequest.getYsqInstability())
            .build();
    }
}
