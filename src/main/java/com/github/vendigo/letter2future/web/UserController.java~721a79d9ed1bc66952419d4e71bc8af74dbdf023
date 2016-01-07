package com.github.vendigo.letter2future.web;

import com.github.vendigo.letter2future.domain.User;
import com.github.vendigo.letter2future.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User newUser) {
        return new ResponseEntity<Map<String, String>>(userService.registerUser(newUser), HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user, HttpServletRequest request) {
        return new ResponseEntity<Map<String, String>>(userService.loginUser(user, request), HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> logoutUser(HttpServletRequest request) {
        return new ResponseEntity<Map<String, String>>(userService.logoutUser(request), HttpStatus.OK);
    }
}
