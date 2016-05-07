package com.github.vendigo.l2f;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class TestConfiguration {
    @Bean(name = "csrfTestToken")
    String csrfTestToken() {
        return "test_csrf_token";
    }

    @Bean(name = "csrfParameterName")
    String csrfParameterName() {
        return "_csrf";
    }

    @Bean(name = "csrfHeaderName")
    String csrfHeaderName() {
        return "X-XSRF-TOKEN";
    }

    @Bean
    CsrfTokenRepository csrfTokenRepository() {
        return new ConstantCsrfTokenRepository(csrfTestToken(), csrfHeaderName(), csrfParameterName());
    }
}
