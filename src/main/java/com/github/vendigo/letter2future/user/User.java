package com.github.vendigo.letter2future.user;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean isEmailVerified;

    User() {
    }

    public User(String name, String email, String password, boolean isEmailVerified) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isEmailVerified = isEmailVerified;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
