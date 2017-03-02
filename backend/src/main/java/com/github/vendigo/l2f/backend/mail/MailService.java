package com.github.vendigo.l2f.backend.mail;

import com.github.vendigo.l2f.backend.letter.Letter;
import com.github.vendigo.l2f.backend.verification.VerificationLetter;
import com.github.vendigo.l2f.backend.verification.VerificationStatus;

public interface MailService {
    void sendLetter(Letter letter);

    VerificationStatus sendVerificationLetter(VerificationLetter verificationLetter);
}
