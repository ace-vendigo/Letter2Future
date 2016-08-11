package com.github.vendigo.l2f.user;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.vendigo.l2f.AbstractIntTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

public class UserRepositoryIntTest extends AbstractIntTest {

    @Autowired
    UserRepository userRepository;
    private User user1 = new User("Dima", "dima@mail.com", "mypass");

    @Test
    @DatabaseSetup(value = "classpath:datasets/oneUser.xml")
    public void findAllUsers() throws Exception {
        List<User> all = (List<User>) userRepository.findAll();
        assertThat(all, hasSize(1));
        assertThat(all, hasItem(
                allOf(
                        hasProperty("username", equalTo("Dima")),
                        hasProperty("password", equalTo("mypass")
                        ))));
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE_ALL)
    @ExpectedDatabase(value = "classpath:datasets/oneUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void saveUser() throws Exception {
        userRepository.save(user1);
    }
}
