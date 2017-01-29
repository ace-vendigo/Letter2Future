package com.github.vendigo.l2f.user;

import com.github.vendigo.l2f.verification.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    VerificationService verificationService;

    @Override
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        verificationService.generateVerificationRecord(user);
        return savedUser;
    }
}
