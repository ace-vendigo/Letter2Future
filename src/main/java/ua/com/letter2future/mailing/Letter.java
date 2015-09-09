package ua.com.letter2future.mailing;

import ua.com.letter2future.auth.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private  User author;
    private String subject;
    private String body;
    private LocalDate creationDate;
    private LocalDate reveiveDate;

    protected Letter() {}

    public Letter(User author, String subject, String body, LocalDate creationDate, LocalDate reveiveDate) {
        this.author = author;
        this.subject = subject;
        this.body = body;
        this.creationDate = creationDate;
        this.reveiveDate = reveiveDate;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", author=" + author +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", creationDate=" + creationDate +
                ", reveiveDate=" + reveiveDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getReveiveDate() {
        return reveiveDate;
    }

    public void setReveiveDate(LocalDate reveiveDate) {
        this.reveiveDate = reveiveDate;
    }
}
