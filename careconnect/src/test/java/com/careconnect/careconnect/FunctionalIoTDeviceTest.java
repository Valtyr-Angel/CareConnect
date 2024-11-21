package com.careconnect.careconnect;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.careconnect.careconnect.config.TestConfigIot;
import com.careconnect.careconnect.models.FunctionalIoTDevice;

/**
 * Testklasse for validering av funksjonaliteten til FunctionalIoTDevice.
 * 
 * Denne klassen utfører enhetstester for å sikre at metodene i FunctionalIoTDevice 
 * fungerer som forventet. Testene validerer operasjoner som å slå enheten av og på, 
 * samt håndtering av scenarioer der enheten allerede er på eller av.
 * 
 * Klassen bruker Spring Boot-testkonfigurasjon og en tilpasset testkonfigurasjon 
 * (TestConfigIot) for å kjøre testene i et isolert miljø.
 * 
 * Eksempel på testscenarier:
 * <pre>
 * - Kontrollere at en enhet kan slås på fra av-status.
 * - Kontrollere at en enhet kan slås av fra på-status.
 * - Håndtere forsøk på å slå en enhet på som allerede er på.
 * - Håndtere forsøk på å slå en enhet av som allerede er av.
 * </pre>
 */

// Kravspesifikasjon 3 - IoT Device
//Karvspesifikasjon 19 - Testkrav



@SpringBootTest
@ContextConfiguration(classes = TestConfigIot.class)
public class FunctionalIoTDeviceTest {

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

