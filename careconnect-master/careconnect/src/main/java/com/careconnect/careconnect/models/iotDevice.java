package com.careconnect.careconnect.models;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class IoTDevice {
    private String deviceId;
    private String deviceName;
    private String location;
    private String status;

    // Constructor
    @Autowired
    public IoTDevice(String deviceId, String deviceName, String location, String status) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.location = location;
        this.status = status;
    }

    // Default constructor for Spring
    public IoTDevice() {}

    // Getters and Setters
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IoTDevice [deviceId=" + deviceId + ", deviceName=" + deviceName +
               ", location=" + location + ", status=" + status + "]";
    }
}
