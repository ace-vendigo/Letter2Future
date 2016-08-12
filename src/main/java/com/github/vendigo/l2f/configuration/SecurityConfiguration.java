package com.github.vendigo.l2f.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    CsrfHeaderFilter csrfHeaderFilter;
    @Autowired
    CsrfTokenRepository csrfTokenRepository;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, active from user where username=?")
                .authoritiesByUsernameQuery("select username, role from user where username=?");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .logout().logoutSuccessUrl("/").and()
                .authorizeRequests()
                .antMatchers(
                        "/resources/**",
                        "/webjars/**",
                        "/partials/public/**",
                        "/",
                        "/news",
                        "/user")
                .permitAll().
                anyRequest().authenticated().and().
                csrf().csrfTokenRepository(csrfTokenRepository).and()
                .addFilterAfter(csrfHeaderFilter, CsrfFilter.class);
    }

    //@Override
    //public void configure(HttpSecurity http) throws Exception {
    //    http
    //                        .formLogin().loginPage("/#/login")
    //                        .and()
    //                        .logout().logoutSuccessUrl("/").and()
    //                        .authorizeRequests()
    //                        .antMatchers(
    //                                "/resources/**",
    //                                "/webjars/**",
    //                                "/partials/public/**",
    //                                "/",
    //                                "/news",
    //                                "/user")
    //                        .permitAll().
    //                        anyRequest().authenticated().and().
    //                        csrf().disable();
    //}

    @Bean
    @Profile("prod")
    CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
