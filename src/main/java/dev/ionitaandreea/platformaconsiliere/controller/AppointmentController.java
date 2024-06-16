package dev.ionitaandreea.platformaconsiliere.controller;


import dev.ionitaandreea.platformaconsiliere.dto.request.AppointmentRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.NotesRequest;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.service.api.AppointmentService;
import dev.ionitaandreea.platformaconsiliere.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read') or hasAuthority('medic:read')")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserServiceImpl userServiceImpl;


}
