package com.github.vendigo.l2f.letter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.vendigo.l2f.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date sendDate = new Date();
    @Column
    @Enumerated(EnumType.STRING)
    private DepartureDelay departureDelay;
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date desiredReceiptDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date actualReceiptDate;
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

    public Date getSendDate() {
        return sendDate;
    }

    public DepartureDelay getDepartureDelay() {
        return departureDelay;
    }

    public Date getDesiredReceiptDate() {
        return desiredReceiptDate;
    }

    public Date getActualReceiptDate() {
        return actualReceiptDate;
    }

    public boolean isReceived() {
        return received;
    }

    public void setActualReceiptDate(Date actualReceiptDate) {
        this.actualReceiptDate = actualReceiptDate;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", user=" + user +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", sendDate=" + sendDate +
                ", departureDelay=" + departureDelay +
                ", desiredReceiptDate=" + desiredReceiptDate +
                ", actualReceiptDate=" + actualReceiptDate +
                ", received=" + received +
                '}';
    }
}
