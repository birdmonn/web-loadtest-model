package com.adss.rif.repository;

import com.adss.rif.entities.StressTestScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StressTestScenarioRepository extends JpaRepository<StressTestScenario, Long> {
}
