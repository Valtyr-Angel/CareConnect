package com.careconnect.iot;

import java.util.List;

public interface IoTDevicePort {

    IoTDevice getDeviceById(String id);  // Hent en enhet etter ID
    List<IoTDevice> getAllDevices();  // Hent alle enheter
    void createDevice(IoTDevice device);  // Opprett en ny enhet
    void updateDeviceStatus(String id, String newStatus);  // Oppdater status på en enhet
}
