package com.adss.rif.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stress_test_sc")
public class StressTestScenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String detail;
    @Column(columnDefinition = "text")
    private String testCase;
    private String concurrent;
    @Column(columnDefinition = "text")
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

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public String getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public boolean isScenarioPass() {
        return scenarioPass;
    }

    public void setScenarioPass(boolean scenarioPass) {
        this.scenarioPass = scenarioPass;
    }
}
