package com.adss.rif.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "request_form")
public class RequestForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //  project detail
    private String projectId;
    @NotNull(message = "ProjectName cannot be empty")
    @NotEmpty(message = "ProjectName cannot be empty")
    private String projectName;
    private String significantLevel;
    private String requestType;
    @NotNull(message = "Contact cannot be empty")
    @NotEmpty(message = "Contact cannot be empty")
    private String contact;
    @NotNull(message = "Department cannot be empty")
    @NotEmpty(message = "Department cannot be empty")
    private String department;
    @NotNull(message = "MobilePhone cannot be empty")
    @NotEmpty(message = "MobilePhone cannot be empty")
    private String mobilePhone;
    private String objective;
    //  application information
    private String url;
    private String env;
    private String envDetail;
    private String languageDev;
    private String languageDevDetail;
    private boolean browserIe;
    private boolean browserFirefox;
    private String securitySupport;

    private boolean systemImpactF5;
    private boolean isSystemImpactLdap;
    private boolean systemImpactFirewall;
    private boolean systemImpactProxy;
    private boolean systemImpactAd;
    private boolean systemImpactSff;
    private boolean systemImpactSiebel;
    private boolean systemImpactSap;
    private boolean systemImpactEai;
    private boolean systemImpactIvr;
    private String systemImpactEtc;
    // load test : user concurrent
    private boolean impactAssessmentEx;
    private boolean impactAssessmentIh;
    private String userConcurrent;
    private boolean loadTestTypeLoadTest;
    private boolean loadTestTypeStressTest;
    private boolean loadTestTypeReliabilityTest;
    //     loadTest Date
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date targetStartDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date targetFinishDate;

    private String remark;
    private String statusRequest = "wait";
    @ManyToOne
//    @Column(updatable = false)
    private UserWeb createByUser;
    @OneToMany(mappedBy = "requestForm")
    private List<LoadTestScenario> loadTestScenarioList = new ArrayList<>();
    @OneToMany(mappedBy = "requestForm")
    private List<StressTestScenario> stressTestScenarioList = new ArrayList<>();
    @OneToMany(mappedBy = "requestForm")
    private List<ReliabilityTestScenario> reliabilityTestScenarioList = new ArrayList<>();
    @OneToMany(mappedBy = "requestForm")
    private List<FileReport> fileReportList = new ArrayList<>();
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSignificantLevel() {
        return significantLevel;
    }

    public void setSignificantLevel(String significantLevel) {
        this.significantLevel = significantLevel;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getEnvDetail() {
        return envDetail;
    }

    public void setEnvDetail(String envDetail) {
        this.envDetail = envDetail;
    }

    public String getLanguageDev() {
        return languageDev;
    }

    public void setLanguageDev(String languageDev) {
        this.languageDev = languageDev;
    }

    public String getLanguageDevDetail() {
        return languageDevDetail;
    }

    public void setLanguageDevDetail(String languageDevDetail) {
        this.languageDevDetail = languageDevDetail;
    }

    public boolean isBrowserIe() {
        return browserIe;
    }

    public void setBrowserIe(boolean browserIe) {
        this.browserIe = browserIe;
    }

    public boolean isBrowserFirefox() {
        return browserFirefox;
    }

    public void setBrowserFirefox(boolean browserFirefox) {
        this.browserFirefox = browserFirefox;
    }

    public String getSecuritySupport() {
        return securitySupport;
    }

    public void setSecuritySupport(String securitySupport) {
        this.securitySupport = securitySupport;
    }

    public boolean isSystemImpactF5() {
        return systemImpactF5;
    }

    public void setSystemImpactF5(boolean systemImpactF5) {
        this.systemImpactF5 = systemImpactF5;
    }

    public boolean isSystemImpactLdap() {
        return isSystemImpactLdap;
    }

    public void setSystemImpactLdap(boolean systemImpactLdap) {
        isSystemImpactLdap = systemImpactLdap;
    }

    public boolean isSystemImpactFirewall() {
        return systemImpactFirewall;
    }

    public void setSystemImpactFirewall(boolean systemImpactFirewall) {
        this.systemImpactFirewall = systemImpactFirewall;
    }

    public boolean isSystemImpactProxy() {
        return systemImpactProxy;
    }

    public void setSystemImpactProxy(boolean systemImpactProxy) {
        this.systemImpactProxy = systemImpactProxy;
    }

    public boolean isSystemImpactAd() {
        return systemImpactAd;
    }

    public void setSystemImpactAd(boolean systemImpactAd) {
        this.systemImpactAd = systemImpactAd;
    }

    public boolean isSystemImpactSff() {
        return systemImpactSff;
    }

    public void setSystemImpactSff(boolean systemImpactSff) {
        this.systemImpactSff = systemImpactSff;
    }

    public boolean isSystemImpactSiebel() {
        return systemImpactSiebel;
    }

    public void setSystemImpactSiebel(boolean systemImpactSiebel) {
        this.systemImpactSiebel = systemImpactSiebel;
    }

    public boolean isSystemImpactSap() {
        return systemImpactSap;
    }

    public void setSystemImpactSap(boolean systemImpactSap) {
        this.systemImpactSap = systemImpactSap;
    }

    public boolean isSystemImpactEai() {
        return systemImpactEai;
    }

    public void setSystemImpactEai(boolean systemImpactEai) {
        this.systemImpactEai = systemImpactEai;
    }

    public boolean isSystemImpactIvr() {
        return systemImpactIvr;
    }

    public void setSystemImpactIvr(boolean systemImpactIvr) {
        this.systemImpactIvr = systemImpactIvr;
    }

    public String getSystemImpactEtc() {
        return systemImpactEtc;
    }

    public void setSystemImpactEtc(String systemImpactEtc) {
        this.systemImpactEtc = systemImpactEtc;
    }

    public boolean isImpactAssessmentEx() {
        return impactAssessmentEx;
    }

    public void setImpactAssessmentEx(boolean impactAssessmentEx) {
        this.impactAssessmentEx = impactAssessmentEx;
    }

    public boolean isImpactAssessmentIh() {
        return impactAssessmentIh;
    }

    public void setImpactAssessmentIh(boolean impactAssessmentIh) {
        this.impactAssessmentIh = impactAssessmentIh;
    }

    public String getUserConcurrent() {
        return userConcurrent;
    }

    public void setUserConcurrent(String userConcurrent) {
        this.userConcurrent = userConcurrent;
    }

    public boolean isLoadTestTypeLoadTest() {
        return loadTestTypeLoadTest;
    }

    public void setLoadTestTypeLoadTest(boolean loadTestTypeLoadTest) {
        this.loadTestTypeLoadTest = loadTestTypeLoadTest;
    }

    public boolean isLoadTestTypeStressTest() {
        return loadTestTypeStressTest;
    }

    public void setLoadTestTypeStressTest(boolean loadTestTypeStressTest) {
        this.loadTestTypeStressTest = loadTestTypeStressTest;
    }

    public boolean isLoadTestTypeReliabilityTest() {
        return loadTestTypeReliabilityTest;
    }

    public void setLoadTestTypeReliabilityTest(boolean loadTestTypeReliabilityTest) {
        this.loadTestTypeReliabilityTest = loadTestTypeReliabilityTest;
    }

    public Date getTargetStartDate() {
        return targetStartDate;
    }

    public void setTargetStartDate(Date targetStartDate) {
        this.targetStartDate = targetStartDate;
    }

    public Date getTargetFinishDate() {
        return targetFinishDate;
    }

    public void setTargetFinishDate(Date targetFinishDate) {
        this.targetFinishDate = targetFinishDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getStatusRequest() {
        return (this.statusRequest == null) ? "wait" : statusRequest;
    }

    public void setStatusRequest(String statusRequest) {
        this.statusRequest = statusRequest;
    }

    public UserWeb getCreateByUser() {
        return createByUser;
    }

    public void setCreateByUser(UserWeb createByUser) {
        this.createByUser = createByUser;
    }

    @JsonIgnore
    public List<LoadTestScenario> getLoadTestScenarioList() {
        return loadTestScenarioList;
    }

    public void setLoadTestScenarioList(List<LoadTestScenario> loadTestScenarioList) {
        this.loadTestScenarioList = loadTestScenarioList;
    }

    @JsonIgnore
    public List<StressTestScenario> getStressTestScenarioList() {
        return stressTestScenarioList;
    }

    public void setStressTestScenarioList(List<StressTestScenario> stressTestScenarioList) {
        this.stressTestScenarioList = stressTestScenarioList;
    }

    @JsonIgnore
    public List<ReliabilityTestScenario> getReliabilityTestScenarioList() {
        return reliabilityTestScenarioList;
    }

    public void setReliabilityTestScenarioList(List<ReliabilityTestScenario> reliabilityTestScenarioList) {
        this.reliabilityTestScenarioList = reliabilityTestScenarioList;
    }

    @JsonIgnore
    public List<FileReport> getFileReportList() {
        return fileReportList;
    }

    public void setFileReportList(List<FileReport> fileReportList) {
        this.fileReportList = fileReportList;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

