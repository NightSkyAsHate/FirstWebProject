package com.nightsky.serverapi.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;
    private LocalDate creationDate;
    private String username;

    public Review() {
    }

    public Review(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getLocalDate() {
        return creationDate;
    }

    public void setLocalDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
