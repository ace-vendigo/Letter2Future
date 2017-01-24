package com.github.vendigo.l2f.letter;

import com.github.vendigo.l2f.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static org.springframework.util.Assert.notNull;

@Service
@Slf4j
public class LetterServiceImpl implements LetterService {
    @Autowired
    LetterRepository letterRepository;
    @Autowired
    RandomUtils randomUtils;

    @Override
    public Letter createLetter(Letter letter) {
        defineActualReceiptDate(letter);
        letter = letterRepository.save(letter);
        log.info("Saved letter {}", letter);
        return letter;
    }

    private void defineActualReceiptDate(Letter letter) {
        if (letter.getDepartureDelay() == DepartureDelay.EXACT_DATE) {
            notNull(letter.getDesiredReceiptDate());
            letter.setActualReceiptDate(letter.getDesiredReceiptDate());
        } else {
            letter.setActualReceiptDate(randomUtils.generateRandomDate(LocalDate.now(), letter.getDepartureDelay()));
        }
    }
}
