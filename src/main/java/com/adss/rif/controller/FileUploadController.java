package com.adss.rif.controller;

import com.adss.rif.entities.FileReport;
import com.adss.rif.entities.RequestForm;
import com.adss.rif.service.FileReportService;
import com.adss.rif.service.RequestFormService;
import com.adss.rif.storage.StorageFileNotFoundException;
import com.adss.rif.storage.StorageService;
import com.adss.rif.utils.PathView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    private final FileReportService fileReportService;
    private final RequestFormService requestFormService;

    @Autowired
    public FileUploadController(StorageService storageService,
                                FileReportService fileReportService,
                                RequestFormService requestFormService) {
        this.storageService = storageService;
        this.fileReportService = fileReportService;
        this.requestFormService = requestFormService;
    }

    @GetMapping("/report/{pathId}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, @PathVariable String pathId) {
        Resource file = storageService.loadAsResource(pathId, filename);
        String originalFileName = fileReportService.findByPath("/report/" + pathId + "/" + filename).getFileName();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + originalFileName + "\"").body(file);
    }

    @PostMapping("/report/uploadReport/{requestFormId}")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] fileList, @PathVariable Long requestFormId) {
        RequestForm requestForm = requestFormService.findById(requestFormId);
        for (MultipartFile file : fileList) {
            file.getOriginalFilename();
            String pathFile = storageService.store(file, "/" + requestForm.getId() + "/");
            fileReportService.create(new FileReport(pathFile, file.getOriginalFilename(), requestForm));
        }
        return "redirect:" + PathView.formView + "/" + requestFormId;
    }

    @PostMapping("/report/deleteFile")
    public String deleteFile(@RequestParam Long fileId, @RequestParam Long requestFormId, Model model, HttpServletRequest request) {
        if (!request.isUserInRole("ADMIN")) {
            return "redirect:" + PathView.index;
        }
        FileReport fileReport = fileReportService.findById(fileId);
        storageService.deleteFileByPath(fileReport.getPath());
        fileReportService.deleteById(fileReport.getId());
        model.addAttribute("requestForm", requestFormService.findById(requestFormId));
        return PathView.formViewAdmin;
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
