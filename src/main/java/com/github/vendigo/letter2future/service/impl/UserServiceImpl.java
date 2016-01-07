package com.github.vendigo.letter2future.service.impl;

import com.github.vendigo.letter2future.service.UserService;
<<<<<<< 721a79d9ed1bc66952419d4e71bc8af74dbdf023
<<<<<<< 7a83e6f432226007973281bcf0c1f18c93c5046e
import com.github.vendigo.letter2future.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.vendigo.letter2future.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

<<<<<<< bcf2638b0cf2f53adf7f2fa8f10d13c6b24c2326
=======
import com.github.vendigo.letter2future.user.UserRepository;
=======
import com.github.vendigo.letter2future.service.UserRepository;
>>>>>>> Registration: repackaging
import org.springframework.beans.factory.annotation.Autowired;
import com.github.vendigo.letter2future.domain.User;
import org.springframework.stereotype.Service;

>>>>>>> Registration: add user service
=======
>>>>>>> Registration and authentication
@Service
public class UserServiceImpl implements UserService{

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
<<<<<<< bcf2638b0cf2f53adf7f2fa8f10d13c6b24c2326
<<<<<<< 7a83e6f432226007973281bcf0c1f18c93c5046e

    @Override
    public Map<String, String> registerUser(User user) {
        User u = userRepository.findByUsername(user.getUsername());

        Map<String, String> responseMessage = new HashMap<>();

        if (u == null) {
            userRepository.save(user);
            responseMessage.put("message", "User added!");
        }
        else {
            responseMessage.put("error", "User already exists!");
        }

        return responseMessage;
    }

    @Override
    public Map<String, String> loginUser(User user, HttpServletRequest request) {
        User u = userRepository.findByUsername(user.getUsername());
        Map<String, String> responseMessage = new HashMap<>();

        if (u == null) {
            responseMessage.put("error", "User not found!");
        }
        else {
            if (user.getPassword().equals(u.getPassword())){
                responseMessage.put("message", "Successfully logged in");
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", u.getUsername());
                responseMessage.put("currentUser", u.getUsername());
            }
            else {
                responseMessage.put("error", "Incorrect password!");
            }
        }
        return responseMessage;
    }

    @Override
    public Map<String, String> logoutUser(HttpServletRequest request) {
        Map<String, String> responseMessage = new HashMap<>();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("currentUser") == null) {
            responseMessage.put("error", "Not logged in!");
        }
        else {
            session.removeAttribute("currentUser");
            responseMessage.put("message", "Logged out!");
        }

        return responseMessage;
=======
    @Override
    public User registerUser(String name, String email, String password) {
        // TODO: Replace 4-th param with real check
        return userRepository.save(new User(name, email, password, false));
>>>>>>> Registration: add user service
=======

    @Override
    public Map<String, String> registerUser(User user) {
        User u = userRepository.findByUsername(user.getUsername());

        Map<String, String> responseMessage = new HashMap<>();

        if (u == null) {
            user.setPassword(encrypt(user.getPassword()));
            userRepository.save(user);
            responseMessage.put("message", "User added!");
        }
        else {
            responseMessage.put("error", "User already exists!");
        }

        return responseMessage;
    }

    @Override
    public Map<String, String> loginUser(User user, HttpServletRequest request) {
        Iterable<User> users = userRepository.findAll();
        for (User usr: users) {
            System.out.println(usr);
        }
        User userFromRepository = userRepository.findByUsername(user.getUsername());
        Map<String, String> responseMessage = new HashMap<>();

        if (userFromRepository == null) {
            responseMessage.put("error", "User not found!");
        }
        else {
            if (encoder.matches(user.getPassword(), userFromRepository.getPassword())){
                responseMessage.put("message", "Successfully logged in");
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", userFromRepository.getUsername());
                responseMessage.put("currentUser", userFromRepository.getUsername());
            }
            else {
                responseMessage.put("error", "Incorrect password!");
            }
        }
        return responseMessage;
    }

    @Override
    public Map<String, String> logoutUser(HttpServletRequest request) {
        Map<String, String> responseMessage = new HashMap<>();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("currentUser") == null) {
            responseMessage.put("error", "Not logged in!");
        }
        else {
            session.removeAttribute("currentUser");
            responseMessage.put("message", "Logged out!");
        }

        return responseMessage;
>>>>>>> Registration and authentication
    }

    private String encrypt(String plainPassword) {
        return UserServiceImpl.encoder.encode(plainPassword);
    }
}
