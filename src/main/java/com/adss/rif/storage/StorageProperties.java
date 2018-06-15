package com.adss.rif.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";
    private String locationReport = "report";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationReport() {
        return locationReport;
    }

    public void setLocationReport(String locationReport) {
        this.locationReport = locationReport;
    }
}
