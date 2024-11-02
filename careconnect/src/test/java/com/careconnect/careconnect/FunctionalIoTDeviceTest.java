package com.careconnect.careconnect;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.careconnect.careconnect.models.FunctionalIoTDevice;


/**
* Enhetstester for FunctionalIoTDevice-klassen.
*
* FunctionalIoTDeviceTest tester funksjonaliteten til FunctionalIoTDevice for å sikre at
* enheten kan skiftes mellom på- og av-tilstander korrekt. Testene verifiserer også at
* statusen til enheten oppdateres i henhold til disse endringene.
*
* Eksempel på bruk:
* <pre>
* FunctionalIoTDeviceTest test = new FunctionalIoTDeviceTest();
* test.testTurnOnDevice();
* </pre>
*/

@SpringBootTest
 class FunctionalIoTDeviceTest {

    @Autowired
    private FunctionalIoTDevice device;

    @Test
    public void testTurnOnDevice() {
        device.setDeviceId("001");
        device.setDeviceName("Heart Monitor");
        device.setLocation("ICU");
        device.setStatus("Off");

        device.turnOn();
        assertTrue(device.isOn());
        assertEquals("On", device.getStatus());
    }

    @Test
    public void testTurnOffDevice() {
        device.setDeviceId("002");
        device.setDeviceName("Ventilator");
        device.setLocation("ER");
        device.setStatus("On");

        device.turnOn(); // Ensure it's on first
        device.turnOff();
        assertFalse(device.isOn());
        assertEquals("Off", device.getStatus());
    }

    @Test
    public void testDeviceAlreadyOn() {
        device.setDeviceId("003");
        device.setDeviceName("ECG Monitor");
        device.setLocation("Ward");
        device.setStatus("Off");

        device.turnOn(); // Turn on the device
        device.turnOn(); // Try to turn it on again
        assertTrue(device.isOn()); // Device should still be on
    }

    @Test
    public void testDeviceAlreadyOff() {
        device.setDeviceId("004");
        device.setDeviceName("Thermometer");
        device.setLocation("Lab");
        device.setStatus("Off");

        device.turnOff(); // Turn off the device
        assertFalse(device.isOn()); // Device should still be off
    }
}

