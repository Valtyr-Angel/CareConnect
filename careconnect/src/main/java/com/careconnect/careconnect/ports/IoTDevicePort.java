package com.careconnect.careconnect.ports;

import java.util.List;

import com.careconnect.careconnect.models.IoTDevice;  // Adjust the package path as necessary

/**
 * Definerer operasjoner for håndtering av IoT-enheter i CareConnect-systemet.
 * 
 * IoTDevicePort-grensesnittet beskriver de nødvendige metodene for å hente, opprette
 * og oppdatere IoT-enheter. Dette grensesnittet implementeres av adaptere eller
 * andre komponenter som håndterer datarelaterte operasjoner for IoT-enhetene.
 * 
 * Eksempel på bruk:
 * <pre>
 * IoTDevicePort devicePort = new IoTDeviceAdapter();  // Kan implementeres av en adapter
 * IoTDevice device = devicePort.getDeviceById("001");
 * devicePort.createDevice(new IoTDevice("002", "Hjerteovervåker", "ICU", "På"));
 * </pre>
 */

  // Kravspesifikasjon 3 - IoT Device***


public interface IoTDevicePort {

    IoTDevice getDeviceById(String id);  // Hent en enhet etter ID
    List<IoTDevice> getAllDevices();  // Hent alle enheter
    void createDevice(IoTDevice device);  // Opprett en ny enhet
    void updateDeviceStatus(String id, String newStatus);  // Oppdater status på en enhet
}