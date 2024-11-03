package com.careconnect.careconnect.port;

public interface LoginPort {
    boolean authenticate(String username, String password);
}
