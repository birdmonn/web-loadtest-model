package com.example.rif.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_form")
public class RequestForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //  project detail
    private String projectId;
    private String projectName;
    private String significantLevel;
    private String requestType;
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
    private boolean isBrowserIe;
    @Column(columnDefinition = "BOOLEAN default false")
    private boolean isBrowserFirefox;
    private String sucuritySupport;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_f5")
    private boolean isSystemImpactF5;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_ldap")
    private boolean isSystemImpactLDAP;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_firewall")
    private boolean isSystemImpactFirewall;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_proxy")
    private boolean isSystemImpactProxy;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_ad")
    private boolean isSystemImpactAd;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_sff")
    private boolean isSystemImpactSff;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_siebel")
    private boolean isSystemImpactSiebel;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_sap")
    private boolean isSystemImpactSap;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_eai")
    private boolean isSystemImpactEai;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_system_impact_Ivr")
    private boolean isSystemImpactIvr;
    private String systemImpactEtc;
    // load test : user concurrent
    @Column(columnDefinition = "BOOLEAN default false", name = "is_impact_Assessment_ex")
    private boolean isImpactAssessmentEX;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_impact_Assessment_ih")
    private boolean isImpactAssessmentIH;
    private String userCocurrent;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_loadtest_type_loadtest")
    private boolean isLoadTestTypeLoadTest;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_loadtest_type_StressTest")
    private boolean isLoadTestTypeStressTest;
    @Column(columnDefinition = "BOOLEAN default false", name = "is_loadtest_type_reliabilitytest")
    private boolean isLoadTestTypeReliabilityTest;
    // loadTest Date
    private Date targetStartDate;
    private Date targetFinishDate;

    private String remark;
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
        return isBrowserIe;
    }

    public void setBrowserIe(boolean browserIe) {
        isBrowserIe = browserIe;
    }

    public boolean isBrowserFirefox() {
        return isBrowserFirefox;
    }

    public void setBrowserFirefox(boolean browserFirefox) {
        isBrowserFirefox = browserFirefox;
    }

    public String getSucuritySupport() {
        return sucuritySupport;
    }

    public void setSucuritySupport(String sucuritySupport) {
        this.sucuritySupport = sucuritySupport;
    }

    public boolean isSystemImpactF5() {
        return isSystemImpactF5;
    }

    public void setSystemImpactF5(boolean systemImpactF5) {
        isSystemImpactF5 = systemImpactF5;
    }

    public boolean isSystemImpactLDAP() {
        return isSystemImpactLDAP;
    }

    public void setSystemImpactLDAP(boolean systemImpactLDAP) {
        isSystemImpactLDAP = systemImpactLDAP;
    }

    public boolean isSystemImpactFirewall() {
        return isSystemImpactFirewall;
    }

    public void setSystemImpactFirewall(boolean systemImpactFirewall) {
        isSystemImpactFirewall = systemImpactFirewall;
    }

    public boolean isSystemImpactProxy() {
        return isSystemImpactProxy;
    }

    public void setSystemImpactProxy(boolean systemImpactProxy) {
        isSystemImpactProxy = systemImpactProxy;
    }

    public boolean isSystemImpactAd() {
        return isSystemImpactAd;
    }

    public void setSystemImpactAd(boolean systemImpactAd) {
        isSystemImpactAd = systemImpactAd;
    }

    public boolean isSystemImpactSff() {
        return isSystemImpactSff;
    }

    public void setSystemImpactSff(boolean systemImpactSff) {
        isSystemImpactSff = systemImpactSff;
    }

    public boolean isSystemImpactSiebel() {
        return isSystemImpactSiebel;
    }

    public void setSystemImpactSiebel(boolean systemImpactSiebel) {
        isSystemImpactSiebel = systemImpactSiebel;
    }

    public boolean isSystemImpactSap() {
        return isSystemImpactSap;
    }

    public void setSystemImpactSap(boolean systemImpactSap) {
        isSystemImpactSap = systemImpactSap;
    }

    public boolean isSystemImpactEai() {
        return isSystemImpactEai;
    }

    public void setSystemImpactEai(boolean systemImpactEai) {
        isSystemImpactEai = systemImpactEai;
    }

    public boolean isSystemImpactIvr() {
        return isSystemImpactIvr;
    }

    public void setSystemImpactIvr(boolean systemImpactIvr) {
        isSystemImpactIvr = systemImpactIvr;
    }

    public String getSystemImpactEtc() {
        return systemImpactEtc;
    }

    public void setSystemImpactEtc(String systemImpactEtc) {
        this.systemImpactEtc = systemImpactEtc;
    }

    public boolean isImpactAssessmentEX() {
        return isImpactAssessmentEX;
    }

    public void setImpactAssessmentEX(boolean impactAssessmentEX) {
        isImpactAssessmentEX = impactAssessmentEX;
    }

    public boolean isImpactAssessmentIH() {
        return isImpactAssessmentIH;
    }

    public void setImpactAssessmentIH(boolean impactAssessmentIH) {
        isImpactAssessmentIH = impactAssessmentIH;
    }

    public String getUserCocurrent() {
        return userCocurrent;
    }

    public void setUserCocurrent(String userCocurrent) {
        this.userCocurrent = userCocurrent;
    }

    public boolean isLoadTestTypeLoadTest() {
        return isLoadTestTypeLoadTest;
    }

    public void setLoadTestTypeLoadTest(boolean loadTestTypeLoadTest) {
        isLoadTestTypeLoadTest = loadTestTypeLoadTest;
    }

    public boolean isLoadTestTypeStressTest() {
        return isLoadTestTypeStressTest;
    }

    public void setLoadTestTypeStressTest(boolean loadTestTypeStressTest) {
        isLoadTestTypeStressTest = loadTestTypeStressTest;
    }

    public boolean isLoadTestTypeReliabilityTest() {
        return isLoadTestTypeReliabilityTest;
    }

    public void setLoadTestTypeReliabilityTest(boolean loadTestTypeReliabilityTest) {
        isLoadTestTypeReliabilityTest = loadTestTypeReliabilityTest;
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
}

