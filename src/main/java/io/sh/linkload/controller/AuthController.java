package io.sh.linkload.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sh.linkload.dto.AuthenticationResponse;
import io.sh.linkload.dto.LoginRequest;
import io.sh.linkload.dto.RegisterRequest;
import io.sh.linkload.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>(registerRequest.getUsername() + " signed up!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("activate/{token}")
    public ResponseEntity<String> activateAccount(@PathVariable String token) {
        authService.activateAccount(token);
        return new ResponseEntity<>("Account Activated!", HttpStatus.OK);
    }
}
