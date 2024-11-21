package com.careconnect.careconnect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.careconnect.careconnect.config.TestConfigScanner;

/**
 * SignalScannerTest er en testklasse for å validere funksjonaliteten til SignalScanner-klassen.
 * Den tester ulike scenarier for signalhåndtering, som godkjenning og avvisning basert på signalets innhold.
 * 
 * Testene er organisert som enhetstester med bruk av JUnit 5 og Spring Boot testing-rammeverk.
 * 
 * Eksempel på bruk:
 * <pre>
 * SpringBootTest
 * ContextConfiguration(classes = TestConfigScanner.class)
 * public class SignalScannerTest { ... }
 * </pre>
 */

// Kravspesifikasjon 2 - Pasientidentifikasjon
//Karvspesifikasjon 19 - Testkrav



/**
* Unit tests for the SignalScanner class.
*
* SignalScannerTest tester funksjonaliteten til SignalScanner ved å verifisere at
* den riktig håndterer forskjellige signaler som kan gi eller nekte tilgang.
* 
* Denne klassen bruker Spring Boot for kontekstsupport og JUnit for enhetstesting. Testene
* dekker scenarioer som gyldig signal, ugyldig signal, null-verdi og tom streng.
*
* Example usage:
* <pre>
* SignalScannerTest test = new SignalScannerTest();
* test.testAuthorizedSignalGrantsAccess();
* </pre>
*/

@SpringBootTest
<<<<<<< HEAD
class SignalScannerTest {
=======
@ContextConfiguration(classes = TestConfigScanner.class)
public class SignalScannerTest {
>>>>>>> main_merge

    @Autowired
    private SignalScanner signalScanner; // Inject SignalScanner as a Spring bean

    @Test
    @DisplayName("Test authorized signal grants access")
    void testAuthorizedSignalGrantsAccess() {
        // Act
        boolean result = signalScanner.scanSignal("AUTHORIZED");

        // Assert
        Assertions.assertTrue(result, "Signal should grant access for authorized users.");
    }

    @Test
    @DisplayName("Test unauthorized signal denies access")
    void testUnauthorizedSignalDeniesAccess() {
        // Act
        boolean result = signalScanner.scanSignal("UNAUTHORIZED");

        // Assert
        Assertions.assertFalse(result, "Signal should deny access for unauthorized users.");
    }

    @Test
    @DisplayName("Test null signal denies access")
    void testNullSignalDeniesAccess() {
        // Act
        boolean result = signalScanner.scanSignal(null);

        // Assert
        Assertions.assertFalse(result, "Signal should deny access for null input.");
    }

    @Test
    @DisplayName("Test empty signal denies access")
    void testEmptySignalDeniesAccess() {
        // Act
        boolean result = signalScanner.scanSignal("");

        // Assert
        Assertions.assertFalse(result, "Signal should deny access for empty string.");
    }

    
}