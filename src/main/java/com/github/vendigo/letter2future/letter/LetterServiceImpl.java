package com.github.vendigo.letter2future.letter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterServiceImpl implements LetterService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    LetterRepository letterRepository;

    @Override
    public void createLetter(Letter letter) {
        log.info("Saved letter {}", letter);
        letterRepository.save(letter);
    }
}
