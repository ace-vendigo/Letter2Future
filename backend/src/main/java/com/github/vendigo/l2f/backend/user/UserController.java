package com.github.vendigo.l2f.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        return new ResponseEntity<>(userService.createUser(newUser), HttpStatus.OK);
    }
}
