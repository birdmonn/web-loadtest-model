package com.adss.rif.repository;

import com.adss.rif.entities.ReliabilityTestScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReliabilityTestScenarioRepository extends JpaRepository<ReliabilityTestScenario, Long> {
}
