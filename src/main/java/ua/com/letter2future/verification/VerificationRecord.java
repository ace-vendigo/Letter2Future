package ua.com.letter2future.verification;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import ua.com.letter2future.user.User;

import javax.persistence.*;

@Entity
public class VerificationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "forumId")
    private User user;

    @Column
    private String verificationCode;

    VerificationRecord() {}

    @VisibleForTesting
    VerificationRecord(User user, String verificationCode) {
        this.user = user;
        this.verificationCode = verificationCode;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationRecord that = (VerificationRecord) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(user, that.user) &&
                Objects.equal(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, user, verificationCode);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("user", user)
                .add("verificationCode", verificationCode)
                .toString();
    }
}
