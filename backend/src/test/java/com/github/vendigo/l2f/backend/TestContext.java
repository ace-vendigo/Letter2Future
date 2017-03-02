package com.github.vendigo.l2f.backend;

import com.github.vendigo.l2f.backend.mail.TestMailService;
import com.github.vendigo.l2f.backend.mail.MailService;
import com.github.vendigo.l2f.backend.security.TestCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@ComponentScan(basePackageClasses = BackEndLauncher.class)
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
