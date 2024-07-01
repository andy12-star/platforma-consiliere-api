package dev.ionitaandreea.platformaconsiliere.repository;

import dev.ionitaandreea.platformaconsiliere.entity.TestResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestResultsRepository extends JpaRepository<TestResults, Long> {

    Optional<TestResults> findByPatient_Id(Long id);

}
