package com.careconnect.careconnect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SignalScannerTest {

    @Autowired
    private SignalScanner signalScanner; // Inject SignalScanner as a Spring bean

    @Test
    @DisplayName("Test authorized signal grants access")
    public void testAuthorizedSignalGrantsAccess() {
        // Act
        boolean result = signalScanner.scanSignal("AUTHORIZED");

        // Assert
        Assertions.assertTrue(result, "Signal should grant access for authorized users.");
    }

    @Test
    @DisplayName("Test unauthorized signal denies access")
    public void testUnauthorizedSignalDeniesAccess() {
        // Act
        boolean result = signalScanner.scanSignal("UNAUTHORIZED");

        // Assert
        Assertions.assertFalse(result, "Signal should deny access for unauthorized users.");
    }

    @Test
    @DisplayName("Test null signal denies access")
    public void testNullSignalDeniesAccess() {
        // Act
        boolean result = signalScanner.scanSignal(null);

        // Assert
        Assertions.assertFalse(result, "Signal should deny access for null input.");
    }

    @Test
    @DisplayName("Test empty signal denies access")
    public void testEmptySignalDeniesAccess() {
        // Act
        boolean result = signalScanner.scanSignal("");

        // Assert
        Assertions.assertFalse(result, "Signal should deny access for empty string.");
    }

    
}