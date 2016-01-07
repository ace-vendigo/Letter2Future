package com.github.vendigo.letter2future.service;

import com.github.vendigo.letter2future.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    Map<String, String> registerUser(User user);
    Map<String, String> loginUser(User user, HttpServletRequest request);
    Map<String, String> logoutUser(HttpServletRequest request);
}
