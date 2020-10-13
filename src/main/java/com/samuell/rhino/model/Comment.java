package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;

    private String description;
    @Nullable
    private Integer spend_time;
    @Nullable
    private Timestamp created_at;
    @Nullable
    private Timestamp edited_at;
    @NonNull
    private boolean was_deleted;

    @JsonBackReference(value = "user-comments")
    @ManyToOne
    @JoinColumn(name="user_id")
    @NonNull
    private User user;

    @JsonBackReference(value = "bug-comments")
    @ManyToOne
    @JoinColumn(name="bug_id")
    @NonNull
    private Bug bug;

    //Constructors
    public Comment() {
    }

    public Comment(Integer id, String description, Integer spend_time, Timestamp created_at, Timestamp edited_at, boolean was_deleted, User user, Bug bug) {
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSpend_time() {
        return spend_time;
    }

    public void setSpend_time(Integer spend_time) {
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
}
