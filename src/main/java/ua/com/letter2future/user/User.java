package ua.com.letter2future.user;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean isEmailVerified;

    User() {}

    @VisibleForTesting
    User(String name, String nickname, String email, String password, boolean isEmailVerified) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.isEmailVerified = isEmailVerified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(isEmailVerified, user.isEmailVerified) &&
                Objects.equal(id, user.id) &&
                Objects.equal(name, user.name) &&
                Objects.equal(nickname, user.nickname) &&
                Objects.equal(email, user.email) &&
                Objects.equal(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, nickname, email, password, isEmailVerified);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("nickname", nickname)
                .add("email", email)
                .add("password", password)
                .add("isEmailVerified", isEmailVerified)
                .toString();
    }
}
