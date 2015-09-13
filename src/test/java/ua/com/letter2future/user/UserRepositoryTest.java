package ua.com.letter2future.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.letter2future.Launcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Launcher.class)
@WebAppConfiguration
@IntegrationTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testSave() throws Exception {
        userRepository.save(new User("username", "nickname", "user@gmai.com", "123", false));

        assertThat(userRepository.count(), equalTo(1L));
    }
}
