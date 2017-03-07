package com.github.vendigo.l2f.verification;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Verification {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long userId;
    @Column
    private String token;
    @Column
    private LocalDate creationDate;
    @Column
    private Boolean verified;

    public Verification(Long userId, String verificationToken) {
        this.userId = userId;
        this.token = verificationToken;
        this.creationDate = LocalDate.now();
        this.verified = false;
    }
}
