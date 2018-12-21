package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "tokentest21")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expired", nullable = false)
    @Type(type = "yes_no")
    private boolean expired;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user")
    @JsonBackReference
    private User user;

    public Token() {

    }

    public Token(String token, boolean expired, User user) {
        this.token = token;
        this.expired = expired;
        this.user = user;
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

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", expired=" + expired +
                ", user=" + user +
                '}';
    }
}
