package TilstandsMonitor;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Tilstandsmonitor {
    private String pasientID;
    private Map<String, Double> sensorData;
    private Map<String, Grenser> grenser;
    private List<String> varsler;

    public Tilstandsmonitor(String pasientID) {
        this.pasientID = pasientID;
        this.sensorData = new HashMap<>();
        this.grenser = new HashMap<>();
        this.varsler = new ArrayList<>();
        settStandardGrenser();
    }

    // Klasse for å representere normale grenser
    private class Grenser {
        double minVerdi;
        double maxVerdi;

        public Grenser(double minVerdi, double maxVerdi) {
            this.minVerdi = minVerdi;
            this.maxVerdi = maxVerdi;
        }
    }

    // Metode for å sette standardgrenser
    private void settStandardGrenser() {
        grenser.put("puls", new Grenser(60, 100));
        grenser.put("blodtrykk_systolisk", new Grenser(90, 120));
        grenser.put("blodtrykk_diastolisk", new Grenser(60, 80));
        // Legg til flere grenser etter behov
    }

    // Metode for å samle inn data fra sensorer
    public void samleInnData(String sensorType, double verdi) {
        sensorData.put(sensorType, verdi);
    }

    // Metode for å analysere data
    public void analysereData() {
        for (Map.Entry<String, Double> entry : sensorData.entrySet()) {
            String sensorType = entry.getKey();
            double verdi = entry.getValue();

            if (grenser.containsKey(sensorType)) {
                Grenser grense = grenser.get(sensorType);
                if (verdi < grense.minVerdi || verdi > grense.maxVerdi) {
                    generereVarsel(sensorType, verdi, grense);
                }
            }
        }
    }

    // Metode for å generere varsel
    private void generereVarsel(String sensorType, double verdi, Grenser grense) {
        String melding = "Varsel: " + sensorType + " er utenfor normale grenser for pasient " + pasientID +
                ". Verdi: " + verdi + ", Normalområde: " + grense.minVerdi + "-" + grense.maxVerdi;
        System.out.println(melding);
        varsler.add(melding);
        // Implementer logikk for å sende varsel til helsepersonell
    }

    // Metode for å loggføre hendelser
    public void loggføreHendelse(String melding) {
        // Implementer logikk for å loggføre meldinger
        System.out.println("Logg for pasient " + pasientID + ": " + melding);
    }

    // Metode for å hente varsler
    public List<String> getVarsler() {
        return varsler;
    }
}
