package com.github.vendigo.l2f.security;

import com.github.vendigo.l2f.AbstractIntTest;
import com.github.vendigo.l2f.user.User;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class SecurityIntTest extends AbstractIntTest {

    @Test
    public void homePageIsAvailableWithoutLogin() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("/"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    public void frontendPathIsOpen() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("/letters"), String.class);
        System.out.println(response);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    public void registrationIsAvailableWithoutLogin() throws Exception {
        User user = new User("test", "test@meta.ua", "password");
        ResponseEntity<String> response = post("api/user/new", user, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void csrfProtectionIsEnabled() throws Exception {
        User user = new User("test", "test@meta.ua", "password");
        ResponseEntity<String> response = template.postForEntity(buildUrl("api/user/new"), user, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
    }

    @Test
    public void staticResourcesAreAvailableWithoutLogin() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("src/app.js"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    public void userEndpointIsSecured() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("api/user"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

    @Test
    public void loginIsSuccess() throws Exception {
        ResponseEntity<String> loginResponse = login();
        assertThat(loginResponse.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void logoutIsSuccess() throws Exception {
        HttpEntity<Object> httpEntity = new HttpEntity<>(performLogin());
        ResponseEntity<Map> response = template.exchange(buildUrl("/api/logout"), HttpMethod.POST, httpEntity, Map.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.FOUND));
    }

    @Test
    public void userEndpointIsAvailableAfterLogin() throws Exception {
        HttpEntity<Object> httpEntity = new HttpEntity<>(performLogin());
        ResponseEntity<Map> response = template.exchange(buildUrl("/api/user"), HttpMethod.GET, httpEntity, Map.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().get("authenticated"), is(true));
    }
}
