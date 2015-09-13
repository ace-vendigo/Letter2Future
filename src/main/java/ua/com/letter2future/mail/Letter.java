package ua.com.letter2future.mail;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import ua.com.letter2future.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User author;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String body;

    private LocalDate creationDate;

    @Column
    private LocalDate receiveDate;

    Letter() {}

    @VisibleForTesting
    Letter(User author, String subject, String body, LocalDate creationDate, LocalDate receiveDate) {
        this.author = author;
        this.subject = subject;
        this.body = body;
        this.creationDate = creationDate;
        this.receiveDate = receiveDate;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return Objects.equal(id, letter.id) &&
                Objects.equal(author, letter.author) &&
                Objects.equal(subject, letter.subject) &&
                Objects.equal(body, letter.body) &&
                Objects.equal(creationDate, letter.creationDate) &&
                Objects.equal(receiveDate, letter.receiveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, author, subject, body, creationDate, receiveDate);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("author", author)
                .add("subject", subject)
                .add("body", body)
                .add("creationDate", creationDate)
                .add("receiveDate", receiveDate)
                .toString();
    }
}
