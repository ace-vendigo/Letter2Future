package com.github.vendigo.l2f.mail;

import com.github.vendigo.l2f.letter.Letter;
import com.github.vendigo.l2f.verification.VerificationLetter;

public interface MailService {
    void sendLetter(Letter letter);

    void sendVerificationLetter(VerificationLetter verificationLetter);
}
