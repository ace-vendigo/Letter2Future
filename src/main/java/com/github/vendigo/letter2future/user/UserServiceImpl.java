package com.github.vendigo.letter2future.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService{

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        } else {
            session.removeAttribute("currentUser");
            responseMessage.put("message", "Logged out!");
        }

        return responseMessage;
    }

    private String encrypt(String plainPassword) {
        return UserServiceImpl.encoder.encode(plainPassword);
    }
}
