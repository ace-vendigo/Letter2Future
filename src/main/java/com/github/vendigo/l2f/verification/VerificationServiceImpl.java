package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.mail.MailService;
import com.github.vendigo.l2f.user.User;
import com.github.vendigo.l2f.user.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Service
@Slf4j
public class VerificationServiceImpl implements VerificationService {
    public static final int TOKEN_LENGTH = 40;
    @Autowired
    VerificationRepository verificationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;
    @Value("${l2f.verification.subject}")
    String letterSubject;
    @Value("${l2f.link}")
    String host;

    @Override
    public Optional<Verification> generateVerificationRecord(User user) {
        if (!user.isActive()) {
            Verification verification = new Verification(user.getId(), RandomStringUtils.randomAlphanumeric(TOKEN_LENGTH));
            verification = verificationRepository.save(verification);
            VerificationLetter verificationLetter = generateVerificationLetter(verification, user);
            mailService.sendVerificationLetter(verificationLetter);
            return Optional.of(verification);
        } else {
            return Optional.empty();
        }
    }

    @SneakyThrows
    private VerificationLetter generateVerificationLetter(Verification verification, User user) {
        VerificationLetter letter = new VerificationLetter();
        letter.setSubject(letterSubject);
        letter.setEmail(user.getEmail());
        InputStream templateFileStream = getClass().getClassLoader()
                .getResourceAsStream("templates/verification-letter-template.html");
        String letterTemplate = IOUtils.toString(templateFileStream);
        String messageBody = letterTemplate.replace("%username%", user.getUsername())
                .replace("%link%", createLink(verification));
        letter.setBody(messageBody);
        return letter;
    }

    private String createLink(Verification verification) {
        return "%host%/api/verification/activate/%token%"
                .replace("%host%", host)
                .replace("%token%", verification.getToken());
    }

    @Override
    public VerificationResult activateUser(String token) {
        Optional<Verification> record = verificationRepository.findByToken(token);

        if (record.isPresent()) {
            Verification verification = record.get();
            if (verification.getVerified()) {
                log.info("Token {} already verified", token);
                return VerificationResult.ALREADY_VERIFIED;
            } else {
                User user = userRepository.findOne(verification.getUserId());
                if (user != null) {
                    verification.setVerified(true);
                    verificationRepository.save(verification);
                    user.setActive(true);
                    userRepository.save(user);
                    log.info("User {} successfully activated", user);
                    return VerificationResult.VERIFIED;
                } else {
                    log.info("User for verification {} is not found", verification);
                    return VerificationResult.UNKNOWN_USER;
                }
            }
        } else {
            return VerificationResult.INVALID_TOKEN;
        }
    }
}
