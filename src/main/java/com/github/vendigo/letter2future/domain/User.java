package com.github.vendigo.letter2future.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique=true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean isEmailVerified;

    public User() {
    }

    public User(String username, String email, String password, boolean isEmailVerified) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEmailVerified = isEmailVerified;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    @Override
    public String toString() {
        return "User: [ username: " + username + ", email: " + email + " password: " + password
                + ", isEmailVerified:" + isEmailVerified + "]";
    }
}
