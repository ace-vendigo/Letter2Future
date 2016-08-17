package com.github.vendigo.l2f.letter;

import com.github.vendigo.l2f.mail.MailService;
import com.github.vendigo.l2f.user.User;
import com.github.vendigo.l2f.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/letter")
public class LetterController {
    @Autowired
    LetterService letterService;
    @Autowired
    UserRepository userRepository;
    //@Autowired
    MailService mailService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Letter> createLetter(@RequestBody Letter letter) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            letter.setUserId(user.get().getId());
            return new ResponseEntity<>(letterService.createLetter(letter), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/sendTest", method = RequestMethod.GET)
    public ResponseEntity<Letter> sendTestLetter() throws MessagingException {
        Letter testLetter = new Letter("test", "Test email", LocalDate.now(), DepartureDelay.LONG_TERM);
        testLetter.setUserId(1L);
        mailService.sendLetter(testLetter);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
