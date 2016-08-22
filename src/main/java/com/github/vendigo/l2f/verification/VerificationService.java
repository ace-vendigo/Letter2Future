package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.user.User;
import org.apache.commons.mail.Email;

import java.util.Optional;

public interface VerificationService {
    Optional<Verification> generateVerificationRecord(User user);
    Email generateVerificationEmail(Verification verification);
    VerificationResult activateUser(String token);
}
