package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.user.User;

public interface VerificationService {
    VerificationStatus initVerification(User user);

    VerificationResult activateUser(String token);

    VerificationStatus resendLetter(String email);
}
