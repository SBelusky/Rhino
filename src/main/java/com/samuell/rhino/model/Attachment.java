package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "attachment")
public class Attachment {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;
    @Lob
    @NonNull
    private String file;
    @NonNull
    private String name;
    @Nullable
    private Timestamp created_at;
    @Nullable
    private Timestamp edited_at;
    @NonNull
    private boolean was_deleted;

    @JsonBackReference(value = "user-attachments")
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @NonNull
    private User user;

    @JsonBackReference(value = "bug-attachments")
    @ManyToOne
    @JoinColumn(name="bug_id", nullable=false)
    @NonNull
    private Bug bug;

    //Constructors
    public Attachment() {
    }

    public Attachment(@Nullable Integer id, @NonNull String file, @NonNull String name, @Nullable Timestamp created_at, @Nullable Timestamp edited_at, boolean was_deleted, @NonNull User user, @NonNull Bug bug) {
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
    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @NonNull
    public String getFile() {
        return file;
    }

    public void setFile(@NonNull String file) {
        this.file = file;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Nullable
    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(@Nullable Timestamp created_at) {
        this.created_at = created_at;
    }

    @Nullable
    public Timestamp getEdited_at() {
        return edited_at;
    }

    public void setEdited_at(@Nullable Timestamp edited_at) {
        this.edited_at = edited_at;
    }

    public boolean isWas_deleted() {
        return was_deleted;
    }

    public void setWas_deleted(boolean was_deleted) {
        this.was_deleted = was_deleted;
    }

    @NonNull
    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }

    @NonNull
    public Bug getBug() {
        return bug;
    }

    public void setBug(@NonNull Bug bug) {
        this.bug = bug;
    }
}
