package com.adss.rif.service;

import com.adss.rif.entities.FileReport;
import java.util.List;

public interface FileReportService {

    List<FileReport> findAll();

    FileReport findById(Long id);

    FileReport findByPath(String path);

    FileReport create(FileReport fileReport);

    FileReport update(Long id, FileReport fileReport);

    void deleteById(Long id);

}
