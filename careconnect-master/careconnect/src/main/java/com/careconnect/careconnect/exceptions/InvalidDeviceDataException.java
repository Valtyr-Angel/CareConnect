package com.careconnect.careconnect.exceptions;

// Ugyldige data eller påloggingsinformasjon
public class InvalidDeviceDataException extends RuntimeException {
    public InvalidDeviceDataException(String message) {
        super("Ugyldig data: " + message);
    }
}
