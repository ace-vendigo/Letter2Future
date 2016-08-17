package com.github.vendigo.l2f.mail;

import com.github.vendigo.l2f.letter.Letter;

public interface MailService {
    void sendLetter(Letter letter);
}
