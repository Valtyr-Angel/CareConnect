package com.careconnect.iot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester funksjonene til FunctionalIoTDevice-klassen.
 *
 * Denne testen verifiserer at FunctionalIoTDevice kan skrus av og på,
 * og at statusen oppdateres korrekt basert på tilstanden.
 */
public class FunctionalIoTDeviceTest {

    private FunctionalIoTDevice device;

    @BeforeEach
    public void setUp() {
        // Initialiser enheten med ønskede verdier
        device = new FunctionalIoTDevice("002", "Hjerteovervåker", "ICU", "Av");
    }

    @Test
    public void testDeviceIsInitiallyOff() {
        assertFalse(device.isOn(), "Enheten skal være av ved oppstart.");
        assertEquals("Av", device.getStatus(), "Statusen skal være 'Av' ved oppstart.");
    }

    @Test
    public void testTurnOnDevice() {
        device.turnOn();
        assertTrue(device.isOn(), "Enheten skal være på etter å ha blitt slått på.");
        assertEquals("On", device.getStatus(), "Statusen skal være 'On' etter å ha blitt slått på.");
    }

    @Test
    public void testTurnOffDevice() {
        device.turnOn();
        assertTrue(device.isOn(), "Enheten skal være på etter å ha blitt slått på.");

        device.turnOff();
        assertFalse(device.isOn(), "Enheten skal være av etter å ha blitt slått av.");
        assertEquals("Off", device.getStatus(), "Statusen skal være 'Off' etter å ha blitt slått av.");
    }

    @Test
    public void testTurnOnDeviceTwice() {
        device.turnOn();
        assertTrue(device.isOn(), "Enheten skal være på etter første påslag.");
        device.turnOn();
        assertTrue(device.isOn(), "Enheten skal fortsatt være på etter andre påslag.");
        assertEquals("On", device.getStatus(), "Statusen skal fortsatt være 'On' etter andre påslag.");
    }

    @Test
    public void testTurnOffDeviceTwice() {
        device.turnOn();
        device.turnOff();
        assertFalse(device.isOn(), "Enheten skal være av etter første avslag.");
        device.turnOff();
        assertFalse(device.isOn(), "Enheten skal fortsatt være av etter andre avslag.");
        assertEquals("Off", device.getStatus(), "Statusen skal fortsatt være 'Off' etter andre avslag.");
    }
}
