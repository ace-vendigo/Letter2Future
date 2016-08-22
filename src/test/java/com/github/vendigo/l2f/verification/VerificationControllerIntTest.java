package com.github.vendigo.l2f.verification;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.vendigo.l2f.AbstractIntTest;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class VerificationControllerIntTest extends AbstractIntTest {
    private TestRestTemplate template;

    @Test
    @DatabaseSetup(value = "classpath:datasets/beforeUserActivation.xml")
    @ExpectedDatabase(value = "classpath:datasets/afterUserActivation.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void activateUser() throws Exception {
        template = new TestRestTemplate();
        ResponseEntity<VerificationResult> result = template.getForEntity(buildUrl("/verification/activate/SeeCr3tTok1en"),
                VerificationResult.class);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(result.getBody(), equalTo(VerificationResult.VERIFIED));
    }
}
