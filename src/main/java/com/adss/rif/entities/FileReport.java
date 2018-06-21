package com.adss.rif.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "file_report")
public class FileReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String fileName;
    @ManyToOne
    private RequestForm requestForm;
    private boolean slaPass;
    @Column(columnDefinition = "text")
    private String detail;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", updatable = false)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public FileReport() {
    }

    public FileReport(String path, String fileName, RequestForm requestForm) {
        this.path = path;
        this.fileName = fileName;
        this.requestForm = requestForm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RequestForm getRequestForm() {
        return requestForm;
    }

    public void setRequestForm(RequestForm requestForm) {
        this.requestForm = requestForm;
    }

    public boolean isSlaPass() {
        return slaPass;
    }

    public void setSlaPass(boolean slaPass) {
        this.slaPass = slaPass;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
