package com.careconnect.careconnect;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.careconnect.careconnect.exceptions.DeviceNotFoundException;
import com.careconnect.careconnect.exceptions.InvalidDeviceDataException;
import com.careconnect.careconnect.models.IoTDevice;
import com.careconnect.careconnect.ports.IoTDevicePort;
import com.careconnect.careconnect.service.IoTDeviceService;

class IoTDeviceServiceTest {

    private IoTDeviceService ioTDeviceService;
    private IoTDevicePort ioTDevicePort;

    @BeforeEach
    void setUp() {
        ioTDevicePort = mock(IoTDevicePort.class);
        ioTDeviceService = new IoTDeviceService(ioTDevicePort);
    }

    @Test
    void testGetDeviceByIdThrowsDeviceNotFoundException() {
        when(ioTDevicePort.getDeviceById("invalid-id")).thenReturn(null);

        assertThrows(DeviceNotFoundException.class, () -> ioTDeviceService.getDeviceById("invalid-id"));
    }

    @Test
    void testCreateDeviceThrowsInvalidDeviceDataException() {
        IoTDevice invalidDevice = new IoTDevice(null, "", "ICU", "På");

        assertThrows(InvalidDeviceDataException.class, () -> ioTDeviceService.createDevice(invalidDevice));
    }
}

