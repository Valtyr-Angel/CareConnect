package com.careconnect.careconnect;

import org.springframework.stereotype.Service;

@Service
public class SignalScanner {

    public boolean scanSignal(String signal) {
        return "AUTHORIZED".equals(signal);
    }
}
