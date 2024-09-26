package access;

public class SignalScanner {

    public boolean scanSignal(String signal) {
        if (signal.equals("AUTHORIZED")) {
            return true; // Gir tilgang
        } else {
            return false; // Nekter tilgang
        }
    }
}