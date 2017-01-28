package com.github.vendigo.l2f;

import com.github.vendigo.l2f.user.User;
import com.github.vendigo.l2f.user.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntTest {

    @Value("${local.server.port}")
    protected int port;
    protected TestRestTemplate template;
    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
        userRepository.deleteAll();
        User user = new User("testUser", "test@i.ua", "pass");
        user.setActive(true);
        userRepository.save(user);
    }

    protected String buildUrl(String path) {
        return "http://localhost:" + port + "/" + path;
    }

    protected HttpHeaders performLogin() {
        ResponseEntity<String> loginResponse = login();
        HttpHeaders headers = new HttpHeaders();
        headers.put("COOKIE", loginResponse.getHeaders().get("Set-Cookie"));
        return headers;
    }

    protected ResponseEntity<String> login() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.set("username", "testUser");
        body.set("password", "pass");
        return template.postForEntity(buildUrl("api/login"), body, String.class);
    }
}
