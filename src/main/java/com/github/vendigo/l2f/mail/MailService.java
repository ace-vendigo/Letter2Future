package com.github.vendigo.l2f.mail;

import com.github.vendigo.l2f.letter.Letter;
import com.github.vendigo.l2f.verification.VerificationLetter;
import com.github.vendigo.l2f.verification.VerificationStatus;

public interface MailService {
    void sendLetter(Letter letter);

    VerificationStatus sendVerificationLetter(VerificationLetter verificationLetter);
}
