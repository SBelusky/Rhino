package com.samuell.rhino.model;

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

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

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

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(id, log.id) &&
                Objects.equals(message, log.message) &&
                Objects.equals(status, log.status) &&
                Objects.equals(created_at, log.created_at) &&
                Objects.equals(user, log.user) &&
                Objects.equals(bug, log.bug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, status, created_at, user, bug);
    }
}
