package com.samuell.rhino.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {
    //Atributes
    @Id
    @GeneratedValue
    private int id;

    private String description;
    private int spend_time;
    private Timestamp created_at;
    private Timestamp edited_at;
    private boolean was_deleted;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="bug_id", nullable=false)
    private Bug bug;

    //Constructors
    public Comment() {
    }

    public Comment(int id, String description, int spend_time, Timestamp created_at, Timestamp edited_at, boolean was_deleted, User user, Bug bug) {
        this.id = id;
        this.description = description;
        this.spend_time = spend_time;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.was_deleted = was_deleted;
        this.user = user;
        this.bug = bug;
    }

    //Getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSpend_time() {
        return spend_time;
    }

    public void setSpend_time(int spend_time) {
        this.spend_time = spend_time;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getEdited_at() {
        return edited_at;
    }

    public void setEdited_at(Timestamp edited_at) {
        this.edited_at = edited_at;
    }

    public boolean isWas_deleted() {
        return was_deleted;
    }

    public void setWas_deleted(boolean was_deleted) {
        this.was_deleted = was_deleted;
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
        Comment comment = (Comment) o;
        return id == comment.id &&
                spend_time == comment.spend_time &&
                was_deleted == comment.was_deleted &&
                Objects.equals(description, comment.description) &&
                Objects.equals(created_at, comment.created_at) &&
                Objects.equals(edited_at, comment.edited_at) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(bug, comment.bug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, spend_time, created_at, edited_at, was_deleted, user, bug);
    }
}
