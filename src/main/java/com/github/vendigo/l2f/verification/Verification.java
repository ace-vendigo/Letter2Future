package com.github.vendigo.l2f.verification;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Verification {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long userId;
    @Column
    private String token;
    @Column
    private LocalDate creationTime;
    @Column
    private Boolean verified;

    public Verification() {
    }

    public Verification(Long userId, String verificationToken) {
        this.userId = userId;
        this.token = verificationToken;
        this.creationTime = LocalDate.now();
        this.verified = false;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Verification that = (Verification) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(userId, that.userId) &&
                Objects.equal(token, that.token) &&
                Objects.equal(creationTime, that.creationTime) &&
                Objects.equal(verified, that.verified);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, userId, token, creationTime, verified);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("userId", userId)
                .add("token", token)
                .add("creationTime", creationTime)
                .add("verified", verified)
                .toString();
    }
}
