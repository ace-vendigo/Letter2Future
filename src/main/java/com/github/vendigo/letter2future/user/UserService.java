package com.github.vendigo.letter2future.user;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    Map<String, String> registerUser(User user);
    Map<String, Object> loginUser(User user, HttpServletRequest request);
    Map<String, String> logoutUser(HttpServletRequest request);
}
