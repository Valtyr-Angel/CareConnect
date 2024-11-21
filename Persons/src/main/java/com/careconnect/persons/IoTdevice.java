package com.careconnect.iot;


/**
<<<<<<<< HEAD:IoT/src/main/java/com/careconnect/iot/IoTDevice.java
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


========
 * Representasjonsklasse for en IoT-enhet.
 * 
 * Denne klassen modellerer en IoT-enhet med egenskaper som enhets-ID, navn, 
 * plassering og status. Klassen brukes til å representere enheter i systemet 
 * og inkluderer grunnleggende metoder for å hente og oppdatere informasjon om enheten.
 * 
 * Eksempel på bruk:
 * <pre>
 * IoTDevice device = new IoTDevice("001", "Temperaturmåler", "Kjøkken", "På");
 * device.setStatus("Av");
 * System.out.println(device);
 * </pre>
 */

// Kravspesifikasjon 3 - IoT Device

@Component
>>>>>>>> main_merge:Persons/src/main/java/com/careconnect/persons/IoTdevice.java
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
<<<<<<<< HEAD:IoT/src/main/java/com/careconnect/iot/IoTDevice.java
    
========
>>>>>>>> main_merge:Persons/src/main/java/com/careconnect/persons/IoTdevice.java

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
