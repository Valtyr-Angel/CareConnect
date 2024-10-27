package com.careconnect.careconnect.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* Representerer en IoT-enhet i CareConnect-systemet.
*
* Klassen iotDevice inneholder grunnleggende informasjon om en IoT-enhet, som enhets-ID,
* enhetsnavn, plassering og status. Den gir metoder for å hente og oppdatere denne informasjonen.
* Denne klassen kan brukes som grunnlag for mer funksjonelle enheter og kan utvides ved arv.
*
* Eksempel på bruk:
* <pre>
* iotDevice device = new iotDevice("001", "Blodtrykksmåler", "Intensivavdeling", "På");
* </pre>
*/

@Component
public class iotDevice {
    private String deviceId;
    private String deviceName;
    private String location;
    private String status;

    // Constructor
    @Autowired
    public iotDevice(String deviceId, String deviceName, String location, String status) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.location = location;
        this.status = status;
    }

    // Default constructor for Spring
    public iotDevice() {}

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
