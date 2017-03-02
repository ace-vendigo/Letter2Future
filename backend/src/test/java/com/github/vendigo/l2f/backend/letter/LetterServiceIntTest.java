package com.github.vendigo.l2f.backend.letter;

import com.github.vendigo.l2f.backend.AbstractIntTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LetterServiceIntTest extends AbstractIntTest {
    @Autowired
    LetterService letterService;

    @Test
    public void createLetter() throws Exception {
        Letter letter = new Letter("Letter", "Hello future!", LocalDate.of(2016, Month.AUGUST, 11),
                DepartureDelay.NEXT_MONTH);
        letter.setUserId(1L);
        Letter savedLetter = letterService.createLetter(letter);
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
