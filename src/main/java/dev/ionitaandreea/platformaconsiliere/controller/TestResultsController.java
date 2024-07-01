package dev.ionitaandreea.platformaconsiliere.controller;

import dev.ionitaandreea.platformaconsiliere.dto.request.TestResultsRequest;
import dev.ionitaandreea.platformaconsiliere.service.api.TestResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/testresults")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read')")
public class TestResultsController {

    private final TestResultsService testResultsService;

    @PostMapping()
    public ResponseEntity<?> saveTestResults(@RequestBody TestResultsRequest testResultsRequest) {
        testResultsService.saveTestResults(testResultsRequest);
        return ResponseEntity.ok("Test results saved successfully");
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTestResultsForPatient(@PathVariable Long id) {
        return ResponseEntity.ok(testResultsService.getResultsForUser(id));
    }
}
