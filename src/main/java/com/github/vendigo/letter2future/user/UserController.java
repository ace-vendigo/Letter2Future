package com.github.vendigo.letter2future.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/current")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> createUser(@RequestBody User newUser) {
        return new ResponseEntity<>(userService.createUser(newUser), HttpStatus.OK);
    }
}
