package com.adss.rif.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reliability_test_sc")
public class ReliabilityTestScenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detail;
    private String dataTest;
    private String concurrent;
    private String other;
    private boolean scenarioPass;
    @ManyToOne
    private RequestForm requestForm;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", updatable = false)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDataTest() {
        return dataTest;
    }

    public void setDataTest(String dataTest) {
        this.dataTest = dataTest;
    }

    public String getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent;
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

    public RequestForm getRequestForm() {
        return requestForm;
    }

    public void setRequestForm(RequestForm requestForm) {
        this.requestForm = requestForm;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public boolean isScenarioPass() {
        return scenarioPass;
    }

    public void setScenarioPass(boolean scenarioPass) {
        this.scenarioPass = scenarioPass;
    }
}
