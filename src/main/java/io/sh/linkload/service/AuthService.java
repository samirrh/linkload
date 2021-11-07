package io.sh.linkload.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.sh.linkload.dto.RegisterRequest;
import io.sh.linkload.model.NotificationEmail;
import io.sh.linkload.model.User;
import io.sh.linkload.model.VerificationToken;
import io.sh.linkload.repository.UserRepository;
import io.sh.linkload.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;

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

}
