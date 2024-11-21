package com.careconnect.careconnect;

import org.springframework.stereotype.Service;

/**
<<<<<<< HEAD
* Service for scanning and validating access signals.
*
* SignalScanner tilbyr en metode for å validere signaler og bestemme om de skal
* gi tilgang basert på en forhåndsdefinert godkjenningsstrøm.
* 
* Denne klassen er designet som en Spring-komponent og kan brukes i applikasjoner
* hvor tilgang gis ved å sjekke om et signal er autorisert.
* 
* Example usage:
* <pre>
* SignalScanner scanner = new SignalScanner();
* boolean isAuthorized = scanner.scanSignal("AUTHORIZED");
* </pre>
*/
=======
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
>>>>>>> main_merge

@Service
public class SignalScanner {

   

    public boolean scanSignal(String signal) {
        return "AUTHORIZED".equals(signal);
    }
}
