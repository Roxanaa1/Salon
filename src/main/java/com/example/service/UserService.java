package com.example.service;

import com.example.entities.User;
import com.example.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.url}")
    private String appUrl;

    public User create(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // generez un token unic
        String verificationToken = UUID.randomUUID().toString();
        user.setVerificationToken(verificationToken);
        // expirarea tokenului
        user.setTokenExpiration(LocalDateTime.now().plusMinutes(10));
        user.setVerifiedAccount(false);

        User savedUser = userRepository.save(user);

        String verificationLink = appUrl + "/api/auth/verify?token=" + verificationToken;

        emailService.sendVerificationEmail(user.getEmail(), verificationLink);

        return savedUser;
    }
    public User verify(String verificationToken) {
        User user = userRepository.findByVerificationToken(verificationToken)
                .orElseThrow(() -> new RuntimeException("User not found or invalid token."));

        if (user.getTokenExpiration() == null || LocalDateTime.now().isAfter(user.getTokenExpiration())) {
            throw new RuntimeException("Verification link has expired.");
        }

        user.setVerifiedAccount(true);
        user.setVerificationToken(null);
        user.setTokenExpiration(null);

        return userRepository.save(user);
    }
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Invalid credentials."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new EntityNotFoundException("Invalid credentials.");
        }

        if (!user.getVerifiedAccount()) {
            throw new RuntimeException("Account is not verified. Please check your email.");
        }

        return user;
    }
}