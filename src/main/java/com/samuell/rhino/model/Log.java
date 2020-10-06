package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "log")
public class Log {
    //Attributes
    @Id
    @GeneratedValue
    private Integer id;

    private String message;
    private String status;
    private Timestamp created_at;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="bug_id", nullable=false)
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
