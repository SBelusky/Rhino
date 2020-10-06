package com.samuell.rhino.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "attachment")
public class Attachment {
    //Atribute
    @Id
    @GeneratedValue
    private int id;

    @Lob
    private String file;

    private String name;
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
    public Attachment() {
    }

    public Attachment(int id, String file, String name, Timestamp created_at, Timestamp edited_at, boolean was_deleted, User user, Bug bug) {
        this.id = id;
        this.file = file;
        this.name = name;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Attachment that = (Attachment) o;
        return id == that.id &&
                was_deleted == that.was_deleted &&
                Objects.equals(file, that.file) &&
                Objects.equals(name, that.name) &&
                Objects.equals(created_at, that.created_at) &&
                Objects.equals(edited_at, that.edited_at) &&
                Objects.equals(user, that.user) &&
                Objects.equals(bug, that.bug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, file, name, created_at, edited_at, was_deleted, user, bug);
    }
}
