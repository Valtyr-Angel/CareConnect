package com.careconnect.careconnect;

import org.springframework.stereotype.Service;

/**
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

@Service
public class SignalScanner {

   

    public boolean scanSignal(String signal) {
        return "AUTHORIZED".equals(signal);
    }
}
