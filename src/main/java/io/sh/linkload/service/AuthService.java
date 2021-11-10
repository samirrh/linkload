package io.sh.linkload.service;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sh.linkload.dto.AuthenticationResponse;
import io.sh.linkload.dto.LoginRequest;
import io.sh.linkload.dto.RegisterRequest;
import io.sh.linkload.model.NotificationEmail;
import io.sh.linkload.model.User;
import io.sh.linkload.model.VerificationToken;
import io.sh.linkload.repository.UserRepository;
import io.sh.linkload.repository.VerificationTokenRepository;
import io.sh.linkload.security.JwtProvider;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setVerified(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);
        emailService.sendMail(new NotificationEmail("Activate Your LinkLoad Account", user.getEmail(),
                "http://localhost:8080/api/auth/activate/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Transactional
    public void activateAccount(String token) {
        VerificationToken verficationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Invalid Token"));
        String username = verficationToken.getUser().getUsername();
        User userToActivate = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User: " + username + " with this token not found"));
        userToActivate.setVerified(true);
        userRepository.save(userToActivate);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(loginRequest.getUsername(), authToken);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new IllegalStateException("User name not found: " + principal.getUsername()));
    }

}
