package com.github.vendigo.letter2future.letter;

import com.github.vendigo.letter2future.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

@Service
public class LetterServiceImpl implements LetterService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    LetterRepository letterRepository;
    @Autowired
    RandomUtils randomUtils;

    @Override
    public void createLetter(Letter letter) {
        defineActualReceiptDate(letter);
        letter = letterRepository.save(letter);
        log.info("Saved letter {}", letter);
    }

    private void defineActualReceiptDate(Letter letter) {
        if (letter.getDepartureDelay() == DepartureDelay.EXACT_DATE) {
            notNull(letter.getDesiredReceiptDate());
            letter.setActualReceiptDate(letter.getDesiredReceiptDate());
        } else {
            letter.setActualReceiptDate(randomUtils.generateRandomDate(letter.getDepartureDelay()));
        }
    }
}
