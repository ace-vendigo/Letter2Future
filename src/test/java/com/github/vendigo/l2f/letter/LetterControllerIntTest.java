package com.github.vendigo.l2f.letter;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.vendigo.l2f.AbstractIntTest;
import com.github.vendigo.l2f.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;

public class LetterControllerIntTest extends AbstractIntTest {
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate("Dima", "mypass");
    }

    @Test
    @DatabaseSetup(value = "classpath:datasets/userWithId.xml")
    public void createLetter() throws Exception {
        Letter letter = new Letter("Letter", "Hello future!", LocalDate.of(2016, Month.AUGUST, 11),
                DepartureDelay.NEXT_MONTH);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        HttpEntity<Letter> request = new HttpEntity<>(letter, headers);
        Letter savedLetter = template.postForObject(new URI(buildUrl("letter/new")), request, Letter.class);
        assertThat(savedLetter, allOf(
                hasProperty("id", notNullValue()),
                hasProperty("userId", equalTo(1L)),
                hasProperty("subject", equalTo("Letter")),
                hasProperty("body", equalTo("Hello future!")),
                hasProperty("sendDate", equalTo(LocalDate.of(2016, Month.AUGUST, 11))),
                hasProperty("departureDelay", equalTo(DepartureDelay.NEXT_MONTH)),
                hasProperty("desiredReceiptDate", nullValue()),
                hasProperty("actualReceiptDate", notNullValue())
        ));
    }

}
