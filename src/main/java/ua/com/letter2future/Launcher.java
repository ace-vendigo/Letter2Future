package ua.com.letter2future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.com.letter2future.auth.User;
import ua.com.letter2future.auth.VerificationRecord;
import ua.com.letter2future.auth.VerificationRecordRepository;
import ua.com.letter2future.auth.UserRepository;
import ua.com.letter2future.mailing.Letter;
import ua.com.letter2future.mailing.MailBox;

import java.time.LocalDate;

@SpringBootApplication
    public class Launcher implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationRecordRepository verificationRecordRepository;

    @Autowired
    MailBox mailBox;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Launcher.class, args);
    }


    // TODO: Create tests from run
    @Override
    public void run(String... strings) throws Exception {
        userRepository.save(new User("aaa", "aaa", "aaaa", "aaaaa", true));
        Letter l = null;
        for(User u: userRepository.findAll()) {
            l = new Letter(u, "Mutamba", "Mutamba or death", LocalDate.now(), LocalDate.now());
            verificationRecordRepository.save(new VerificationRecord(u, "abracadabra!"));
            mailBox.save(l);
        }

        for(VerificationRecord verificationRecord : verificationRecordRepository.findAll()) {
            System.out.println(verificationRecord.getUser() + " " + verificationRecord.getVerificationCode());
        }
        System.out.println(mailBox.findOne(l != null ? l.getId() : null));
    }
}
