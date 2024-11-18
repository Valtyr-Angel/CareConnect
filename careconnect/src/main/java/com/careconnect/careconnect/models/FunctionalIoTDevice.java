package com.careconnect.careconnect.models;

import org.springframework.stereotype.Component;
/**
* Representerer en funksjonell IoT-enhet med mulighet for å endre tilstand (på/av).
*
* FunctionalIoTDevice er en utvidelse av iotDevice og legger til funksjonalitet
* for å skru enheten av og på. Enheten har en innebygd statusindikator for å 
* spore om den er aktiv.
*
* Eksempel på bruk:
* <pre>
* FunctionalIoTDevice device = new FunctionalIoTDevice("002", "Hjerteovervåker", "ICU", "Av");
* device.turnOn();
* </pre>
*/
@Component
public class FunctionalIoTDevice extends IoTdevice {
    private boolean isOn;

    // Constructor
    public FunctionalIoTDevice(String deviceId, String deviceName, String location, String status) {
        super(deviceId, deviceName, location, status);
        this.isOn = false; // Default state is off
    }

    // Default constructor for Spring
    public FunctionalIoTDevice() {
        super();
        this.isOn = false;
    }

    // Method to turn on the device
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            setStatus("On");
        }
    }

    // Method to turn off the device
    public void turnOff() {
        if (isOn) {
            isOn = false;
            setStatus("Off");
        }
    }

    // Method to check if the device is on or off
    public boolean isOn() {
        return isOn;
    }
}

