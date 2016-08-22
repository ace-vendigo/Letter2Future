package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.mail.MailService;
import com.github.vendigo.l2f.user.User;
import com.github.vendigo.l2f.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VerificationServiceTest {

    @Mock
    private VerificationRepository verificationRepository;
    @Mock
    private MailService mailService;
    @Mock
    private UserRepository userRepository;
    private VerificationService verificationService;

    @Before
    public void setUp() throws Exception {
        verificationService = new VerificationServiceImpl(verificationRepository, userRepository, mailService);
    }

    @Test
    public void generateVerificationInactiveUser() throws Exception {
        User user = new User(1L, "Dima", "dima@in.ua", "qwerty");
        Verification verification = verificationService.generateVerificationRecord(user).get();
        assertThat(verification, allOf(
                hasProperty("userId", equalTo(1L)),
                hasProperty("token", not(isEmptyString())),
                hasProperty("creationTime", notNullValue()),
                hasProperty("verified", is(false))
        ));
        verify(verificationRepository).save(verification);
    }

    @Test
    public void generateVerificationActiveUser() throws Exception {
        User user = new User(1L, "Dima", "dima@in.ua", "myPass");
        user.setActive(true);
        Optional<Verification> result = verificationService.generateVerificationRecord(user);
        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void activateAlreadyActiveUser() throws Exception {
        User user = new User(1L, "Dima", "dima@in.ua", "myPass");
        user.setActive(true);
        when(userRepository.findOne(1L)).thenReturn(user);

        Verification verification = new Verification(1L, "token1");
        verification.setVerified(true);
        when(verificationRepository.findByToken("token1")).thenReturn(Optional.of(verification));
        VerificationResult result = verificationService.activateUser("token1");
        assertThat(result, is(VerificationResult.ALREADY_VERIFIED));
    }

    @Test
    public void activateWithInvalidToken() throws Exception {
        User user = new User(1L, "Dima", "dima@in.ua", "myPass");
        when(userRepository.findOne(1L)).thenReturn(user);
        when(verificationRepository.findByToken(anyString())).thenReturn(Optional.empty());

        VerificationResult result = verificationService.activateUser("token1");
        assertThat(result, is(VerificationResult.INVALID_TOKEN));
    }

    @Test
    public void activateWithUnknownUser() throws Exception {
        Verification verification = new Verification(1L, "token1");
        when(verificationRepository.findByToken("token1")).thenReturn(Optional.of(verification));
        VerificationResult result = verificationService.activateUser("token1");
        assertThat(result, is(VerificationResult.UNKNOWN_USER));
    }
}
