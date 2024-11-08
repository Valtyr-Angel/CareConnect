package com.careconnect.iot;


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

public class IoTDevice {
    private String deviceId;
    private String deviceName;
    private String location;
    private String status;

    // Constructor
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
        return "ioTDevice [deviceId=" + deviceId + ", deviceName=" + deviceName +
               ", location=" + location + ", status=" + status + "]";
    }
}
