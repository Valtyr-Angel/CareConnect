package com.careconnect.careconnect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class SignalScannerTest {

    @Test
    @DisplayName("Test authorized signal grants access")
    public void testAuthorizedSignalGrantsAccess() {
        // Arrange
        SignalScanner scanner = new SignalScanner();

        // Act
        boolean result = scanner.scanSignal("AUTHORIZED");

        // Assert
        Assertions.assertTrue(result, "Signal should grant access for authorized users.");
    }

    @Test
    @DisplayName("Test unauthorized signal denies access")
    public void testUnauthorizedSignalDeniesAccess() {
        // Arrange
        SignalScanner scanner = new SignalScanner();

        // Act
        boolean result = scanner.scanSignal("UNAUTHORIZED");

        // Assert
        Assertions.assertFalse(result, "Signal should deny access for unauthorized users.");
    }
}
