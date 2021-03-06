package com.adss.rif.serviceimpl;

import com.adss.rif.entities.FileReport;
import com.adss.rif.repository.FileReportRepository;
import com.adss.rif.service.FileReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileReportServiceImpl implements FileReportService {
    private FileReportRepository fileReportRepository;

    @Autowired
    public FileReportServiceImpl(FileReportRepository fileReportRepository) {
        this.fileReportRepository = fileReportRepository;
    }

    @Override
    public List<FileReport> findAll() {
        return fileReportRepository.findAll();
    }

    @Override
    public FileReport findById(Long id) {
        return fileReportRepository.getOne(id);
    }

    @Override
    public FileReport create(FileReport fileReport) {
        return fileReportRepository.saveAndFlush(fileReport);
    }

    @Override
    public FileReport update(Long id, FileReport fileReport) {
        fileReport.setId(id);
        return fileReportRepository.saveAndFlush(fileReport);
    }

    @Override
    public FileReport findByPath(String path) {
        return fileReportRepository.findByPath(path);
    }

    @Override
    public void deleteById(Long id) {
        fileReportRepository.deleteById(id);
    }

    @Override
    public void updateSlaDetail(List<FileReport> fileReportList) {
        for (FileReport fileReportItem : fileReportList){
            FileReport fileReportTemp = findById(fileReportItem.getId());
            fileReportTemp.setDetail(fileReportItem.getDetail());
            fileReportTemp.setSlaPass(fileReportItem.isSlaPass());
            update(fileReportTemp.getId(),fileReportTemp);
        }
    }
}
