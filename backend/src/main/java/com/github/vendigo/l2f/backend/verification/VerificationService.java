package com.github.vendigo.l2f.backend.verification;

import com.github.vendigo.l2f.backend.user.User;

public interface VerificationService {
    VerificationStatus initVerification(User user);

    VerificationResult activateUser(String token);

    VerificationStatus resendLetter(String email);
}
