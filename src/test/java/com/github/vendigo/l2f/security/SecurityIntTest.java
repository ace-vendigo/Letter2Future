package com.github.vendigo.l2f.security;

import com.github.vendigo.l2f.AbstractIntTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public void getNewsIsAvailableWithoutLogin() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("api/news"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    public void staticResourcesAreAvailableWithoutLogin() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("dist/app.js"), String.class);
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
        ResponseEntity<String> loginResponse = performLogin();
        assertThat(loginResponse.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Ignore
    @Test
    public void userEndpointIsAvailableAfterLogin() throws Exception {
        ResponseEntity<String> loginResponse = performLogin();
        HttpEntity<Object> httpEntity = new HttpEntity<>(loginResponse.getHeaders());
        ResponseEntity<String> response = template.exchange(buildUrl("/api/user"), HttpMethod.GET, httpEntity, String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }
}
