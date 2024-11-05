package com.careconnect.careconnect.LoginService;

import com.careconnect.careconnect.port.LoginPort;

public class LoginService {
    private final LoginPort loginPort;

    public LoginService(LoginPort loginPort) {
        this.loginPort = loginPort;
    }

    public boolean login(String username, String password) {
        return loginPort.authenticate(username, password);
    }
}
