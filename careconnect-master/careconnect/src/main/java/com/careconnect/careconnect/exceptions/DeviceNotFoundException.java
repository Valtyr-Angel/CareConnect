package com.careconnect.careconnect.exceptions;


// Enhet ikke funnet
public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String id) {
        super("Enhet med ID " + id + " ble ikke funnet.");
    }
}
