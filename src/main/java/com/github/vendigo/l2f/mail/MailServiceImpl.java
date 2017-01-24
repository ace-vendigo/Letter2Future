package com.github.vendigo.l2f.mail;

import com.github.vendigo.l2f.letter.Letter;
import com.github.vendigo.l2f.user.User;
import com.github.vendigo.l2f.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private UserRepository userRepository;
    @Value("${l2f.mail.address}")
    private String adress;
    @Value("${l2f.mail.password}")
    private String password;
    @Value("${l2f.mail.host}")
    private String host;
    @Value("${l2f.mail.port}")
    private int port;

    @Override
    public void sendLetter(Letter letter) throws EmailException {
        User user = userRepository.findOne(letter.getUserId());

        Email email = prepareEmail();
        email.setSubject(letter.getSubject());
        email.setMsg(letter.getBody());
        email.addTo(user.getEmail());

        log.info("Sending email to: {} with subject: {}", user.getEmail(), letter.getSubject());
        email.send();
    }

    private Email prepareEmail() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(host);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator(adress, password));
        email.setSSLOnConnect(true);
        email.setFrom(adress);
        return email;
    }
}
