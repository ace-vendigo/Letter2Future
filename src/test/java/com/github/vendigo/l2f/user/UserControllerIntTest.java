package com.github.vendigo.l2f.user;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.vendigo.l2f.AbstractIntTest;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserControllerIntTest extends AbstractIntTest {

    private TestRestTemplate template = new TestRestTemplate();
    private User user1 = new User("Dima", "dima@mail.com", "mypass");

    @Test
    public void getNews() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("news"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE_ALL)
    public void createNewUser() throws Exception {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(_csrf, csrfToken);
        HttpEntity<User> request = new HttpEntity<>(user1, headers);
        URI uri = new URI(buildUrl("user/new"));
        String response = template.postForObject(uri, request, String.class);
        System.out.println("Post response: " + response);
    }
}