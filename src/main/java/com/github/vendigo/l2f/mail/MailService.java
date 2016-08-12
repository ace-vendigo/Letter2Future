package com.github.vendigo.l2f.mail;

import com.github.vendigo.l2f.user.User;

public interface MailService {
    void sendEmail(User user);
}
