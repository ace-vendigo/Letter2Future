package com.github.vendigo.l2f;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public abstract class AbstractIntTest {
    public static final String SET_COOKIE = "Set-Cookie";
    @Value("${local.server.port}")
    protected int port;
    @Autowired
    @Qualifier("csrfTestToken")
    protected String csrfToken;

    @Autowired
    @Qualifier("csrfParameterName")
    protected String _csrf;

    protected String getSessionId(ResponseEntity<String> response) {
        return response.getHeaders().get(SET_COOKIE).stream().
                filter(s -> s.startsWith("JSESSIONID=")).findFirst().get();
    }

    protected String getSessionId(TestRestTemplate template) {
        ResponseEntity<String> response = template.getForEntity(buildUrl(""), String.class);
        return getSessionId(response);
    }

    protected String buildUrl(String path) {
        return "http://localhost:" + port + "/" + path;
    }
}
