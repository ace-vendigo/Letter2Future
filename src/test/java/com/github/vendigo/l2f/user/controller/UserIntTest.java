package com.github.vendigo.l2f.user.controller;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.vendigo.l2f.IntTestTemplate;
import com.github.vendigo.l2f.user.User;
import com.github.vendigo.l2f.user.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserIntTest extends IntTestTemplate {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CsrfTokenRepository csrfTokenRepository;

    private RestTemplate template = new TestRestTemplate();

    private User user1 = new User("Dima", "dima@mail.com", "mypass");

    @Test
    @DatabaseSetup(value = "classpath:datasets/oneUser.xml")
    public void testFindAllUsers() throws Exception {
        List<User> all = (List<User>) userRepository.findAll();
        assertThat(all, hasSize(1));
        assertThat(all, hasItem(user1));
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE_ALL)
    @ExpectedDatabase(value = "classpath:datasets/oneUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveUser() throws Exception {
        userRepository.save(user1);
    }

    @Test
    public void testGetNews() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("news"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE_ALL)
    public void testCreateNewUser() throws Exception {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(_csrf, csrfToken);
        HttpEntity<User> request = new HttpEntity<>(user1, headers);
        URI uri = new URI(buildUrl("user/new"));
        String response = template.postForObject(uri, request, String.class);
        System.out.println("Post response: "+response);
    }
}
