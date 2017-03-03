package com.github.vendigo.l2f.backend.security;

import com.github.vendigo.l2f.backend.AbstractIntTest;
import com.github.vendigo.l2f.backend.user.User;
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
    public void newsAreAvailableWithoutLogin() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("news"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    public void registrationIsAvailableWithoutLogin() throws Exception {
        User user = new User("test", "test@meta.ua", "password");
        ResponseEntity<String> response = post("user/new", user, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void csrfProtectionIsEnabled() throws Exception {
        User user = new User("test", "test@meta.ua", "password");
        ResponseEntity<String> response = template.postForEntity(buildUrl("user/new"), user, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
    }

    @Test
    public void userEndpointIsSecured() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("user"), String.class);
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
        ResponseEntity<Map> response = template.exchange(buildUrl("logout"), HttpMethod.POST, httpEntity, Map.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.FOUND));
    }

    @Test
    public void userEndpointIsAvailableAfterLogin() throws Exception {
        HttpEntity<Object> httpEntity = new HttpEntity<>(performLogin());
        ResponseEntity<Map> response = template.exchange(buildUrl("user"), HttpMethod.GET, httpEntity, Map.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().get("authenticated"), is(true));
    }
}
