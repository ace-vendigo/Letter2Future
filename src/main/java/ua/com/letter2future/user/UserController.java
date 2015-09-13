package ua.com.letter2future.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.letter2future.verification.VerificationRecordCreator;
import ua.com.letter2future.verification.VerificationRecordRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationRecordRepository verificationRecordRepository;

    private static boolean EMAIL_VERIFIED = true;

    /**
     * Using basic authentication
     * User login method is pretty straightforward:
     * 1. Get user credentials from request
     * 2. Search given nickname in users table
     * 3. If found user check equality of provided password
     * and return result of authorization
     * if not found return appropriate error
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        if (nickname == null || password == null) {
            model.put("error", "Incomplete credentials.");
            return model;
        }

        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            model.put("error", "User not found.");
            return model;
        }

        if (user.getPassword().equals(password)) {
            model.put("login", "Login user");
        } else {
            model.put("error", "Password incorrect");
        }

        return model;
    }

    /**
     * Register new user in system
     * 1. Get user information from request
     * 2. Check it, validation performed on
     * client side.
     * 3. Try to add new user end return result
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();

        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || nickname == null || email == null || password == null) {
            model.put("error", "Invalid registration data.");
            return model;
        }


        User user = userRepository.findByNickname(nickname);
        if (user != null) {
            model.put("error", "User already exists");
            return model;
        }

        user = new User(name, nickname, email, password, !EMAIL_VERIFIED);
        user = userRepository.save(user);

        verificationRecordRepository.save(
                VerificationRecordCreator.createVerificationRecord(user));

        model.put("message", "User saved. Email confirmation code created.");

        return model;
    }


    // add_message
}
