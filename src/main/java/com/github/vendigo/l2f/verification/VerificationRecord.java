package com.github.vendigo.l2f.verification;

import com.github.vendigo.l2f.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VerificationRecord {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne(optional = false)
    User user;
    @Column
    String verificationToken;
    @Column
    LocalDate creationTime;
    @Column
    Boolean verified;
    @Column
    Boolean mailSend;

    public VerificationRecord() {
    }

    public VerificationRecord(User user, String verificationToken, Boolean verified, Boolean mailSend) {
        this.user = user;
        this.verificationToken = verificationToken;
        this.verified = verified;
        this.mailSend = mailSend;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public Boolean getVerified() {
        return verified;
    }

    public Boolean getMailSend() {
        return mailSend;
    }
}
