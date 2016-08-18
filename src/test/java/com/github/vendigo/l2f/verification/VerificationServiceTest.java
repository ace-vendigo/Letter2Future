package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.mail.MailService;
import com.github.vendigo.l2f.user.User;
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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VerificationServiceTest {

    @Mock
    private VerificationRepository verificationRepository;
    @Mock
    private MailService mailService;
    private VerificationService verificationService;

    @Before
    public void setUp() throws Exception {
        verificationService = new VerificationServiceImpl(verificationRepository, mailService);
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
}
