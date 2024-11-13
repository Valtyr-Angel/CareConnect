package com.careconnect.careconnect.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FunctionalIoTDevice extends IoTDevice {
    private boolean isOn;

    // Constructor
    public FunctionalIoTDevice(String deviceId, String deviceName, String location, String status) {
        super(deviceId, deviceName, location, status);
        this.isOn = false; // Default state is off
    }

    // Default constructor for Spring
    @Autowired
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
