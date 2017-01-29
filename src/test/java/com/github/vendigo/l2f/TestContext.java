package com.github.vendigo.l2f;

import com.github.vendigo.l2f.mail.MailService;
import com.github.vendigo.l2f.mail.TestMailService;
import com.github.vendigo.l2f.security.TestCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@ComponentScan(basePackageClasses = Launcher.class)
public class TestContext {
    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        return new TestCsrfTokenRepository();
    }

    @Bean
    public MailService mailService() {
        return new TestMailService();
    }
}
