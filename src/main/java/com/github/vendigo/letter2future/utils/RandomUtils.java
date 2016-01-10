package com.github.vendigo.letter2future.utils;

import com.github.vendigo.letter2future.letter.DepartureDelay;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class RandomUtils {
    public Date generateRandomDate(DepartureDelay departureDelay) {
        LocalDate localDate = LocalDate.now();
        long daysToAdd = 0;
        long monthToAdd = 0;
        long yearsToAdd = 0;

        //TODO Complete method

        return null;
    }
}
