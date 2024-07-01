package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.request.TestResultsRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.TestResultsResponse;
import dev.ionitaandreea.platformaconsiliere.entity.TestResults;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.TestResultsRepository;
import dev.ionitaandreea.platformaconsiliere.repository.UserRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.TestResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestResultsServiceImpl implements TestResultsService {

    private final TestResultsRepository testResultsRepository;
    private final UserRepository userRepository;

    @Override
    public TestResultsResponse getResultsForUser(Long userId) {
        TestResults results = testResultsRepository.findByPatient_Id(userId).orElse(null);
        if (results == null) {
            return TestResultsResponse.builder().patientId(userId).build();
        }
        return Mapper.toResultsResponse(results);
    }

    @Override
    public void saveTestResults(TestResultsRequest testResultsRequest) {

        User patient = userRepository.findById(testResultsRequest.getPatientId())
            .orElseThrow(IllegalArgumentException::new);

        TestResults testResults = Mapper.toResults(testResultsRequest, patient);

        testResultsRepository.save(testResults);
    }
}
