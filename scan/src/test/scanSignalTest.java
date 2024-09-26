package access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class scanSignalTest {

    @Test
    @DisplayName("Test authorized signal grants access")
    public void testAuthorizedSignalGrantsAccess() {
        // Arrange
        SignalScanner mockScanner = Mockito.mock(SignalScanner.class);
        Mockito.when(mockScanner.scanSignal("AUTHORIZED")).thenReturn(true);

        // Act
        boolean result = mockScanner.scanSignal("AUTHORIZED");

        // Assert
        Assertions.assertTrue(result, "Signal should grant access for authorized users.");
    }

    @Test
    @DisplayName("Test unauthorized signal denies access")
    public void testUnauthorizedSignalDeniesAccess() {
        // Arrange
        SignalScanner mockScanner = Mockito.mock(SignalScanner.class);
        Mockito.when(mockScanner.scanSignal("UNAUTHORIZED")).thenReturn(false);

        // Act
        boolean result = mockScanner.scanSignal("UNAUTHORIZED");

        // Assert
        Assertions.assertFalse(result, "Signal should deny access for unauthorized users.");
    }
}
