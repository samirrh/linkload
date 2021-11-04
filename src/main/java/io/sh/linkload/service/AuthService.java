package io.sh.linkload.service;

import org.springframework.stereotype.Service;

import io.sh.linkload.dto.RegisterRequest;
import io.sh.linkload.model.User;

@Service
public class AuthService {
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setVerified(false);
    }
}
