package com.github.vendigo.l2f.mail;

import com.github.vendigo.l2f.letter.Letter;
import org.apache.commons.mail.EmailException;

import javax.mail.MessagingException;

public interface MailService {
    void sendLetter(Letter letter) throws MessagingException, EmailException;
}
