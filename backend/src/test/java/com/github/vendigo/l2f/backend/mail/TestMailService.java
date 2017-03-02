package com.github.vendigo.l2f.backend.mail;

import com.github.vendigo.l2f.backend.letter.Letter;
import com.github.vendigo.l2f.backend.verification.VerificationLetter;
import com.github.vendigo.l2f.backend.verification.VerificationStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMailService implements MailService {
    @Override
    public void sendLetter(Letter letter) {
        log.info("Sending {}", letter);
    }

    @Override
    public VerificationStatus sendVerificationLetter(VerificationLetter verificationLetter) {
        log.info("Sending {}", verificationLetter);
        return VerificationStatus.LETTER_SENT;
    }
}
