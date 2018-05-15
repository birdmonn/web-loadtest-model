package com.adss.rif.repository;

import com.adss.rif.entities.LoadTestScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoadTestScenarioRepository extends JpaRepository<LoadTestScenario, Long> {


}
