package com.github.vendigo.letter2future.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/principal")
    public Principal user(Principal user) {
        return user;
    }
}
