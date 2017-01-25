package com.github.vendigo.l2f.user;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.vendigo.l2f.AbstractIntTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserControllerIntTest extends AbstractIntTest {

    private TestRestTemplate template;
    private User user1 = new User("Dima", "dima@mail.com", "mypass");

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    @Test
    public void getNews() throws Exception {
        ResponseEntity<String> response = template.getForEntity(buildUrl("api/news"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE_ALL)
    public void createNewUser() throws Exception {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        HttpEntity<User> request = new HttpEntity<>(user1, headers);
        User user = template.postForObject(new URI(buildUrl("api/user/new")), request, User.class);
        assertThat(user,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("username", equalTo("Dima")),
                        hasProperty("password", equalTo("mypass")),
                        hasProperty("email", equalTo("dima@mail.com")),
                        hasProperty("role", equalTo("ROLE_USER")
                        )));
    }
}
