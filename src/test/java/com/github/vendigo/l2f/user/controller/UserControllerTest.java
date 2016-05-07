package com.github.vendigo.l2f.user.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.vendigo.l2f.Launcher;
import com.github.vendigo.l2f.user.User;
import com.github.vendigo.l2f.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Launcher.class)
@WebAppConfiguration
@IntegrationTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    private RestTemplate template = new TestRestTemplate();


    private User user1 = new User("Dima", "dima@mail.com", "mypass");

    @Test
    @DatabaseSetup(value = "classpath:datasets/oneUser.xml")
    public void testFindAllUsers() throws Exception {
        List<User> all = (List<User>)userRepository.findAll();
        assertThat(all, hasSize(1));
        assertThat(all, hasItem(user1));
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE_ALL)
    @ExpectedDatabase(value = "classpath:datasets/oneUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveUser() throws Exception {
        userRepository.save(user1);
    }
}
