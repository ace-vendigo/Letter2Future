package com.github.vendigo.l2f.letter;

import com.github.vendigo.l2f.user.User;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private User user;
    @Column(nullable = false)
    @Size(min = 1, max = 100)
    private String subject;
    @Column(nullable = false)
    @Size(min = 1, max = 3000)
    private String body;
    @Column
    private LocalDate sendDate;
    @Column
    @Enumerated(EnumType.STRING)
    private DepartureDelay departureDelay;
    @Column
    private LocalDate desiredReceiptDate;
    @Column
    private LocalDate actualReceiptDate;
    @Column
    private boolean received = false;

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

    public DepartureDelay getDepartureDelay() {
        return departureDelay;
    }

    public LocalDate getDesiredReceiptDate() {
        return desiredReceiptDate;
    }

    public LocalDate getActualReceiptDate() {
        return actualReceiptDate;
    }

    public boolean isReceived() {
        return received;
    }

    public void setActualReceiptDate(LocalDate actualReceiptDate) {
        this.actualReceiptDate = actualReceiptDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return received == letter.received &&
                Objects.equals(id, letter.id) &&
                Objects.equals(user, letter.user) &&
                Objects.equals(subject, letter.subject) &&
                Objects.equals(body, letter.body) &&
                Objects.equals(sendDate, letter.sendDate) &&
                departureDelay == letter.departureDelay &&
                Objects.equals(desiredReceiptDate, letter.desiredReceiptDate) &&
                Objects.equals(actualReceiptDate, letter.actualReceiptDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, subject, body, sendDate, departureDelay, desiredReceiptDate, actualReceiptDate, received);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("user", user)
                .add("subject", subject)
                .add("body", body)
                .add("sendDate", sendDate)
                .add("departureDelay", departureDelay)
                .add("desiredReceiptDate", desiredReceiptDate)
                .add("actualReceiptDate", actualReceiptDate)
                .add("received", received)
                .toString();
    }
}
