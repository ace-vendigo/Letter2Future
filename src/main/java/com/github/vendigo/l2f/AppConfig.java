package com.github.vendigo.l2f;

import com.github.vendigo.l2f.mail.MailService;
import com.github.vendigo.l2f.mail.MailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    MailService mailService() {
        return new MailServiceImpl();
    }
}
