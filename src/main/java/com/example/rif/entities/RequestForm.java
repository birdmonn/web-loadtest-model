package com.example.rif.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "request_form")
public class RequestForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //  project detail
    private String projectId;
    @NotNull
    @Min(1)
    private String projectName;
    private String significantLevel;
    private String requestType;
    @NotNull
    @Min(1)
    private String contact;
    private String mobilePhone;
    private String objective;
    //  application information
    private String url;
    private String env;
    private String envDetail;
    private String languageDev;
    private String languageDevDetail;
    @Column(columnDefinition = "BOOLEAN default false")
    private boolean browserIe;
    @Column(columnDefinition = "BOOLEAN default false")
    private boolean browserFirefox;
    private String securitySupport;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_f5")
    private boolean systemImpactF5;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_ldap")
    private boolean isSystemImpactLdap;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_firewall")
    private boolean systemImpactFirewall;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_proxy")
    private boolean systemImpactProxy;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_ad")
    private boolean systemImpactAd;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_sff")
    private boolean systemImpactSff;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_siebel")
    private boolean systemImpactSiebel;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_sap")
    private boolean systemImpactSap;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_eai")
    private boolean systemImpactEai;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_Ivr")
    private boolean systemImpactIvr;
    private String systemImpactEtc;
    // load test : user concurrent
    @Column(columnDefinition = "BOOLEAN default false", name = "is_impact_Assessment_ex")
    private boolean impactAssessmentEx;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_impact_Assessment_ih")
    private boolean impactAssessmentIh;
    private String userConcurrent;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_loadtest_type_loadtest")
    private boolean loadTestTypeLoadTest;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_loadtest_type_StressTest")
    private boolean loadTestTypeStressTest;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_loadtest_type_reliabilitytest")
    private boolean loadTestTypeReliabilityTest;
    // loadTest Date
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date targetStartDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date targetFinishDate;

    private String remark;
    private String statusRequest = "wait";
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
        return (this.statusRequest == null) ?"wait" :statusRequest;
    }

    public void setStatusRequest(String statusRequest) {
        this.statusRequest = statusRequest;
    }
}

