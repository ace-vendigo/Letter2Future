package com.github.vendigo.letter2future.letter;

import com.github.vendigo.letter2future.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    private User user;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String body;

    @Column
    private LocalDate sendDate;

    public Letter() {
    }

    public Letter(User user, String subject, String body, LocalDate sendDate) {
        this.user = user;
        this.subject = subject;
        this.body = body;
        this.sendDate = sendDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", user=" + user +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
