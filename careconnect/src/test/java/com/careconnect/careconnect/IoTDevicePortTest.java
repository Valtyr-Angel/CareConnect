package com.careconnect.careconnect;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.careconnect.careconnect.models.IoTDevice;
import com.careconnect.careconnect.ports.IoTDevicePort;

class IoTDevicePortTest {

    private IoTDevicePort ioTDevicePort;
    private IoTDevice testDevice;

    @BeforeEach
    void setUp() {
        ioTDevicePort = mock(IoTDevicePort.class);  // Mock IoTDevicePort
        testDevice = new IoTDevice("001", "Hjerteovervåker", "ICU", "På");
    }

    @Test
    void testGetDeviceById() {
        // Setup mock behavior
        when(ioTDevicePort.getDeviceById("001")).thenReturn(testDevice);

        // Execute the method to be tested
        IoTDevice device = ioTDevicePort.getDeviceById("001");

        // Validate results
        assertNotNull(device, "Enheten skal ikke være null");
        assertEquals("001", device.getDeviceId(), "Enhets-ID skal være '001'");
        assertEquals("På", device.getStatus(), "Status skal være 'På'");
        verify(ioTDevicePort, times(1)).getDeviceById("001");
    }

    @Test
    void testGetAllDevices() {
        // Setup mock data
        List<IoTDevice> devices = new ArrayList<>();
        devices.add(testDevice);
        devices.add(new IoTDevice("002", "Temperatursensor", "Room 1", "Av"));
        when(ioTDevicePort.getAllDevices()).thenReturn(devices);

        // Execute the method to be tested
        List<IoTDevice> result = ioTDevicePort.getAllDevices();

        // Validate results
        assertNotNull(result, "Listen av enheter skal ikke være null");
        assertEquals(2, result.size(), "Listen skal inneholde to enheter");
        verify(ioTDevicePort, times(1)).getAllDevices();
    }

    @Test
    void testCreateDevice() {
        // Create a new IoT device
        IoTDevice newDevice = new IoTDevice("003", "Bevegelsessensor", "Hallway", "Av");

        // No return type for void methods, so we verify the method call
        ioTDevicePort.createDevice(newDevice);

        // Validate that the method was called
        verify(ioTDevicePort, times(1)).createDevice(newDevice);
    }

    @Test
    void testUpdateDeviceStatus() {
        // Setup
        String newStatus = "Av";

        // Act: update the status of an existing device
        ioTDevicePort.updateDeviceStatus("001", newStatus);

        // Validate that the update was called with correct parameters
        verify(ioTDevicePort, times(1)).updateDeviceStatus("001", newStatus);
    }
}