package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.request.TestResultsRequest;
import dev.ionitaandreea.platformaconsiliere.dto.response.TestResultsResponse;

public interface TestResultsService {

    TestResultsResponse getResultsForUser(Long userId);

    void saveTestResults(TestResultsRequest testResultsRequest);


}
