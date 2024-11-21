package com.careconnect.careconnect.adapters;
import java.util.ArrayList;
import java.util.List;

import com.careconnect.careconnect.models.IoTDevice;
import com.careconnect.careconnect.ports.IoTDevicePort;

/**
 * Representerer en adapter for IoT-enheter som implementerer IoTDevicePort-grensesnittet.
 * 
 * IoTDeviceAdapter fungerer som et mellomledd mellom applikasjonen og IoT-enhetene,
 * og gir metoder for å hente, opprette og oppdatere IoT-enheter. Klassen simulerer en lagring
 * av IoT-enheter ved hjelp av en liste og gir CRUD-operasjoner for enhetene.
 * 
 * Eksempel på bruk:
 * <pre>
 * IoTDeviceAdapter adapter = new IoTDeviceAdapter();
 * IoTDevice device = new IoTDevice("001", "Blodtrykksmåler", "Intensivavdeling", "På");
 * adapter.createDevice(device);
 * IoTDevice fetchedDevice = adapter.getDeviceById("001");
 * </pre>
 */

 // Kravspesifikasjon 3 - IoT Device***


public class IoTDeviceAdapter implements IoTDevicePort {

    private List<IoTDevice> devices = new ArrayList<>();  // Simulerer en lagring

    @Override
    public IoTDevice getDeviceById(String id) {
        for (IoTDevice device : devices) {
            if (device.getDeviceId().equals(id)) {
                return device;  // Returnerer IoTDevice som DTO
            }
        }
        return null;
    }

    @Override
    public List<IoTDevice> getAllDevices() {
        return devices;  // Returnerer listen med IoTDevice som DTO-er
    }

    @Override
    public void createDevice(IoTDevice device) {
        devices.add(device);  // Legger til en ny IoTDevice i lageret
    }

    @Override
    public void updateDeviceStatus(String id, String newStatus) {
        for (IoTDevice device : devices) {
            if (device.getDeviceId().equals(id)) {
                device.setStatus(newStatus);  // Oppdaterer status på enheten
            }
        }
    }
}
