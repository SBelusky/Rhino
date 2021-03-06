package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log")
public class Log {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;
    @NonNull
    private String message;
    @NonNull
    private String status;
    @Nullable
    private Timestamp created_at;

    @JsonBackReference(value = "user-logs")
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @NonNull
    private User user;

    @JsonBackReference(value = "bug-logs")
    @ManyToOne
    @JoinColumn(name="bug_id", nullable=false)
    @NonNull
    private Bug bug;

    //Constructors
    public Log() {
    }

    public Log(Integer id, String message, String status, Timestamp created_at, User user, Bug bug) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.created_at = created_at;
        this.user = user;
        this.bug = bug;
    }

    //Getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }
}
