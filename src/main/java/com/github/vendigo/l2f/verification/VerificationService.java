package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.user.User;

import java.util.Optional;

public interface VerificationService {
    Optional<Verification> generateVerificationRecord(User user);
    VerificationResult activateUser(String token);
}
