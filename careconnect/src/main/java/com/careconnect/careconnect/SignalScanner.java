package com.careconnect.careconnect;

import org.springframework.stereotype.Service;

/**
 * SignalScanner er en tjenesteklasse som håndterer NFC-skanning for pasientidentifikasjon.
 * 
 * Denne klassen implementerer funksjonalitet for å skanne signaler (f.eks. fra NFC-armbånd) 
 * og avgjør om signalet gir tilgang til pasientjournaler eller ikke. Signalene valideres basert 
 * på om de er autoriserte eller ikke.
 * 
 * Eksempel på bruk:
 * <pre>
 * SignalScanner signalScanner = new SignalScanner();
 * boolean accessGranted = signalScanner.scanSignal("AUTHORIZED");
 * </pre>
 */

// Kravspesifikasjon 2 - Pasientidentifikasjon

@Service
public class SignalScanner {

    public boolean scanSignal(String signal) {
        return "AUTHORIZED".equals(signal);
    }
}
