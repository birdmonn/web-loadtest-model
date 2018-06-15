package com.adss.rif.repository;

import com.adss.rif.entities.FileReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileReportRepository extends JpaRepository<FileReport, Long> {

    @Query("SELECT fr FROM FileReport fr WHERE fr.path = ?1 ")
    FileReport findByPath(String path);
}
