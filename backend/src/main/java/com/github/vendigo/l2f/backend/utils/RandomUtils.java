package com.github.vendigo.l2f.backend.utils;

import com.github.vendigo.l2f.backend.letter.DepartureDelay;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class RandomUtils {

    private Random random = new Random();

    /**
     * Generates random date according to the given {@link DepartureDelay}.
     * <ul>
     * <li>{@link DepartureDelay#NEXT_MONTH} - some day in next month</li>
     * <li>{@link DepartureDelay#FOR_YEAR} - some day in the next 12 months (starting from the next month)</li>
     * <li>{@link DepartureDelay#LONG_TERM} - some day in the next 3 years (starting from the next year)</li>
     * </ul>
     *
     * @throws IllegalArgumentException when used with {@link DepartureDelay#EXACT_DATE}
     */
    public LocalDate generateRandomDate(LocalDate fromDate, DepartureDelay delay) {
        if (delay == DepartureDelay.EXACT_DATE) {
            throw new IllegalArgumentException("Illegal DepartureDelay");
        }
        LocalDate date = fromDate;
        date = rollYear(delay, date);
        date = rollMonth(delay, date);
        date = date.withDayOfMonth(generateRandomDayOfMonth(date));
        return date;
    }

    private LocalDate rollMonth(DepartureDelay delay, LocalDate date) {
        switch (delay) {
            case NEXT_MONTH:
                return date.plusMonths(1);
            case FOR_YEAR:
                return date.plusMonths(randomBetween(1, 12));
            case LONG_TERM:
                return date.withMonth(randomBetween(1, 12));
            default:
                throw new IllegalArgumentException("Illegal DepartureDelay");
        }
    }

    private LocalDate rollYear(DepartureDelay delay, LocalDate date) {
        return delay == DepartureDelay.LONG_TERM ? date.plusYears(randomBetween(1, 3)) : date;
    }

    private int generateRandomDayOfMonth(LocalDate date) {
        return randomBetween(1, date.getMonth().length(date.isLeapYear()));
    }

    private int randomBetween(int from, int to) {
        return from + random.nextInt(to);
    }

    void setRandom(Random random) {
        this.random = random;
    }
}
