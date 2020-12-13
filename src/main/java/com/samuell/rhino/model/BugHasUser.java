package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.embedded_key.BugHasUserKey;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity(name = "bug_has_user")
@Table(name = "bug_has_user")
public class BugHasUser {
    //Attributes
    @EmbeddedId
    private BugHasUserKey id;

    @NonNull
    private String type;

    @JsonBackReference(value = "bug-bugHasUsers")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("bug_id")
    @JoinColumn(name = "bug_id")
    @NonNull
    private Bug bug;

    @JsonBackReference(value = "user-bugHasUsers")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    //Constructors
    public BugHasUser() {
    }

    public BugHasUser(BugHasUserKey id, @NonNull String type, @NonNull Bug bug, @NonNull User user) {
        this.id = id;
        this.type = type;
        this.bug = bug;
        this.user = user;
    }

    //Getters & setters
    public BugHasUserKey getId() {
        return id;
    }

    public void setId(BugHasUserKey id) {
        this.id = id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public Bug getBug() {
        return bug;
    }

    public void setBug(@NonNull Bug bug) {
        this.bug = bug;
    }

    @NonNull
    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }
}
