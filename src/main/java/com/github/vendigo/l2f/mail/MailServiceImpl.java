package com.github.vendigo.l2f.mail;

import com.github.vendigo.l2f.letter.Letter;
import com.github.vendigo.l2f.user.User;
import com.github.vendigo.l2f.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Value("${l2f.email}")
    private String emailFrom;

    @Override
    public void sendLetter(Letter letter) throws MessagingException {
        User user = userRepository.findOne(letter.getUserId());

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(user.getEmail());
        helper.setFrom(emailFrom);
        helper.setSubject(letter.getSubject());
        helper.setText(letter.getBody());

        javaMailSender.send(mail);
    }
}
