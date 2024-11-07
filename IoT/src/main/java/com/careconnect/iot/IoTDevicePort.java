package com.careconnect.iot;

import java.util.List;

public interface IoTDevicePort {

    iotDevice getDeviceById(String id);  // Hent en enhet etter ID
    List<iotDevice> getAllDevices();  // Hent alle enheter
    void createDevice(iotDevice device);  // Opprett en ny enhet
    void updateDeviceStatus(String id, String newStatus);  // Oppdater status på en enhet
}
