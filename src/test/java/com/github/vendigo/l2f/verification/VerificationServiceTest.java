package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.AbstractIntTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsNot.not;

public class VerificationServiceTest extends AbstractIntTest {
    @Autowired
    private VerificationServiceImpl verificationService;

    @Test
    public void generateVerificationInactiveUser() throws Exception {
        user.setActive(false);
        userRepository.save(user);
        Verification verification = verificationService.generateVerificationRecord(user).get();
        assertThat(verification, allOf(
                hasProperty("userId", notNullValue()),
                hasProperty("token", not(isEmptyString())),
                hasProperty("creationTime", notNullValue()),
                hasProperty("verified", is(false))
        ));
    }

    @Test
    public void generateVerificationActiveUser() throws Exception {
        Optional<Verification> result = verificationService.generateVerificationRecord(user);
        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void activateAlreadyActiveUser() throws Exception {
        user.setActive(false);
        userRepository.save(user);
        verificationService.generateVerificationRecord(user);
        user.setActive(true);
        userRepository.save(user);
        Verification verification = verificationRepository.findByUserId(user.getId()).get();
        verification.setVerified(true);
        verificationRepository.save(verification);

        VerificationResult result = verificationService.activateUser(verification.getToken());
        assertThat(result, is(VerificationResult.ALREADY_VERIFIED));
    }

    @Test
    public void activateWithInvalidToken() throws Exception {
        VerificationResult result = verificationService.activateUser("token1");
        assertThat(result, is(VerificationResult.INVALID_TOKEN));
    }

    @Test
    public void activateWithUnknownUser() throws Exception {
        Verification verification = new Verification(2L, "token1");
        verificationRepository.save(verification);

        VerificationResult result = verificationService.activateUser("token1");
        assertThat(result, is(VerificationResult.UNKNOWN_USER));
    }
}
