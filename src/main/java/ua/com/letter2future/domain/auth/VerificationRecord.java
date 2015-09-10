package ua.com.letter2future.domain.auth;

import javax.persistence.*;

@Entity
public class VerificationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "forumId")
    private User user;
    private String verificationCode;

    protected VerificationRecord() {}

    public VerificationRecord(User user, String verificationCode) {
        this.user = user;
        this.verificationCode = verificationCode;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String toString() {
        return "VerificationRecord{" +
                "id=" + id +
                ", user=" + user +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
