package com.careconnect.iot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careconnect.iot.FunctionalIoTDevice;


@RestController
@RequestMapping("/api/devices")
public class FunctionalIoTDeviceController {

    private List<FunctionalIoTDevice> devices = new ArrayList<>();

    @Autowired
    private FunctionalIoTDevice functionalIoTDevice;

    // GET-mapping for å hente alle enhetene
    @GetMapping
    public List<FunctionalIoTDevice> getAllDevices() {
        return devices;
    }

    // GET-mapping for å hente en enhet ved ID
    @GetMapping("/{deviceId}")
    public FunctionalIoTDevice getDeviceById(@PathVariable String deviceId) {
        return devices.stream()
                .filter(device -> device.getDeviceId().equals(deviceId))
                .findFirst()
                .orElse(null); // Returnerer null hvis ikke enhet er funnet
    }

    // POST-mapping for å legge til en ny enhet
    @PostMapping
    public String createDevice(@RequestBody FunctionalIoTDevice newDevice) {
        devices.add(newDevice);
        return "Device " + newDevice.getDeviceName() + " created successfully!";
    }

    // PUT-mapping for å oppdatere en enhet (f.eks. slå på enheten)
    @PutMapping("/{deviceId}/turnOn")
    public String turnOnDevice(@PathVariable String deviceId) {
        FunctionalIoTDevice device = getDeviceById(deviceId);
        if (device != null) {
            device.turnOn();
            return "Device " + deviceId + " turned on.";
        }
        return "Device not found.";
    }

    // PUT-mapping for å oppdatere en enhet (f.eks. slå av enheten)
    @PutMapping("/{deviceId}/turnOff")
    public String turnOffDevice(@PathVariable String deviceId) {
        FunctionalIoTDevice device = getDeviceById(deviceId);
        if (device != null) {
            device.turnOff();
            return "Device " + deviceId + " turned off.";
        }
        return "Device not found.";
    }
}
