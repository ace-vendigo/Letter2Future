package com.github.vendigo.l2f.backend.letter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Long userId;
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

    public Letter(String subject, String body, LocalDate sendDate, DepartureDelay departureDelay) {
        this.subject = subject;
        this.body = body;
        this.sendDate = sendDate;
        this.departureDelay = departureDelay;
    }

    public Letter(String subject, String body, LocalDate sendDate, DepartureDelay departureDelay,
                  LocalDate desiredReceiptDate) {
        this.subject = subject;
        this.body = body;
        this.sendDate = sendDate;
        this.departureDelay = departureDelay;
        this.desiredReceiptDate = desiredReceiptDate;
    }
}
