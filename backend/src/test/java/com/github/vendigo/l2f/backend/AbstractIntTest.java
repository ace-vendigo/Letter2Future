package com.github.vendigo.l2f.backend;

import com.github.vendigo.l2f.backend.user.User;
import com.github.vendigo.l2f.backend.verification.VerificationRepository;
import com.github.vendigo.l2f.backend.user.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestContext.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntTest {

    @Value("${local.server.port}")
    protected int port;
    protected TestRestTemplate template;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected VerificationRepository verificationRepository;
    protected User user;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
        userRepository.deleteAll();
        verificationRepository.deleteAll();
        User user = new User("testUser", "test@i.ua", "pass");
        user.setActive(true);
        this.user = userRepository.save(user);
    }

    protected String buildUrl(String path) {
        return "http://localhost:" + port + "/" + path;
    }

    protected <T> ResponseEntity<T> post(String url, Object body, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-XSRF-TOKEN", "test_csrf_token");
        HttpEntity<Object> request = new HttpEntity<>(body, headers);
        return template.postForEntity(buildUrl(url), request, clazz);
    }

    protected HttpHeaders performLogin() {
        ResponseEntity<String> loginResponse = login();
        HttpHeaders headers = new HttpHeaders();
        headers.put("COOKIE", loginResponse.getHeaders().get("Set-Cookie"));
        headers.set("X-XSRF-TOKEN", "test_csrf_token");
        return headers;
    }

    protected ResponseEntity<String> login() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.set("username", "testUser");
        body.set("password", "pass");
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-XSRF-TOKEN", "test_csrf_token");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        return template.postForEntity(buildUrl("api/login"), request, String.class);
    }
}
