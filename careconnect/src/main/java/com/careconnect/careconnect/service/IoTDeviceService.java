package com.careconnect.careconnect.service;
import java.util.List;

import com.careconnect.careconnect.exceptions.DeviceNotFoundException;
import com.careconnect.careconnect.exceptions.InvalidDeviceDataException;
import com.careconnect.careconnect.models.IoTDevice;
import com.careconnect.careconnect.ports.IoTDevicePort;

/**
 * Tjenesteklasse for håndtering av operasjoner relatert til IoT-enheter.
 * 
 * IoTDeviceService fungerer som et mellomlag mellom IoTDevicePort (som er ansvarlig for
 * datalagring og operasjoner på IoT-enhetene) og resten av applikasjonen. Denne klassen
 * gir enkle metoder for å hente, opprette og oppdatere IoT-enheter ved å bruke
 * IoTDevicePort. Den kan utvides for å legge til ekstra logikk i fremtidige krav.
 * 
 * Eksempel på bruk:
 * <pre>
 * IoTDeviceService deviceService = new IoTDeviceService(new IoTDeviceAdapter());
 * IoTDevice device = deviceService.getDeviceById("001");
 * deviceService.createDevice(new IoTDevice("003", "Temperaturmåler", "Kjøkken", "På"));
 * </pre>
 */

public class IoTDeviceService {

    private final IoTDevicePort ioTDevicePort;

    public IoTDeviceService(IoTDevicePort ioTDevicePort) {
        this.ioTDevicePort = ioTDevicePort;
    }

    public IoTDevice getDeviceById(String id) {
        IoTDevice device = ioTDevicePort.getDeviceById(id);
        if (device == null) {
            throw new DeviceNotFoundException(id);
        }
        return device;
    }

    public List<IoTDevice> getAllDevices() {
        return ioTDevicePort.getAllDevices();  // Bruker IoTDevice som DTO
    }

    public void createDevice(IoTDevice device) {
        if (device == null || device.getDeviceId() == null || device.getDeviceName().isEmpty()) {
            throw new InvalidDeviceDataException("Device ID og navn kan ikke være tomme.");
        }
        ioTDevicePort.createDevice(device);
    }

    public void updateDeviceStatus(String id, String newStatus) {
        IoTDevice device = ioTDevicePort.getDeviceById(id);
        if (device == null) {
            throw new DeviceNotFoundException(id);
        }
        ioTDevicePort.updateDeviceStatus(id, newStatus);
    }
}