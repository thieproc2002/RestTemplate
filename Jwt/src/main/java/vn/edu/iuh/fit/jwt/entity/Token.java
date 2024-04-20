package vn.edu.iuh.fit.jwt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="token")
public class Token {
    @Id
    private long id;//=id nguoi dung
    private String token;

    public Token() {
    }

    public Token(long id, String token) {
        this.id = id;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
