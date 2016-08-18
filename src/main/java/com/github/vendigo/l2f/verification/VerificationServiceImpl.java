package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.mail.MailService;
import com.github.vendigo.l2f.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final int TOKEN_LENGTH = 40;
    private VerificationRepository verificationRepository;
    private MailService mailService;

    @Autowired
    public VerificationServiceImpl(VerificationRepository verificationRepository, MailService mailService) {
        this.verificationRepository = verificationRepository;
        this.mailService = mailService;
    }

    @Override
    public Optional<Verification> generateVerificationRecord(User user) {
        if (user.isActive()) {
            log.info("User {} is already active, verificationRecord hasn't been generated", user.getUsername());
            return Optional.empty();
        } else {
            Verification verification = new Verification(user.getId(), RandomStringUtils.randomAlphanumeric(TOKEN_LENGTH));
            verificationRepository.save(verification);
            return Optional.of(verification);
        }
    }

    @Override
    public Email generateVerificationEmail(Verification verification) {
        return null;
    }
}
