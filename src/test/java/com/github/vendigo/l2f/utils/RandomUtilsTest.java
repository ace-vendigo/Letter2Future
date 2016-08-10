package com.github.vendigo.l2f.utils;

import com.github.vendigo.l2f.letter.DepartureDelay;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RandomUtilsTest {
    private RandomUtils randomUtils;

    @Before
    public void setUp() throws Exception {

        Random mockedRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return Math.min(5, bound - 1);
            }
        };
        randomUtils = new RandomUtils();
        randomUtils.setRandom(mockedRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failWithExactDate() throws Exception {
        randomUtils.generateRandomDate(LocalDate.of(2016, Month.JULY, 10), DepartureDelay.EXACT_DATE);
    }

    @Test
    public void nextMonth() throws Exception {
        LocalDate generatedDate = randomUtils.generateRandomDate(LocalDate.of(2016, Month.AUGUST, 15),
                DepartureDelay.NEXT_MONTH);
        assertThat(generatedDate, is(LocalDate.of(2016, Month.SEPTEMBER, 6)));
    }

    @Test
    public void forYear() throws Exception {
        LocalDate generatedDate = randomUtils.generateRandomDate(LocalDate.of(2016, Month.AUGUST, 15),
                DepartureDelay.FOR_YEAR);
        assertThat(generatedDate, is(LocalDate.of(2017, Month.FEBRUARY, 6)));
    }

    @Test
    public void longTerm() throws Exception {
        LocalDate generatedDate = randomUtils.generateRandomDate(LocalDate.of(2016, Month.AUGUST, 15),
                DepartureDelay.LONG_TERM);
        assertThat(generatedDate, is(LocalDate.of(2019, Month.JUNE, 6)));
    }
}
