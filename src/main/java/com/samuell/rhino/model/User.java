package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @Nullable
    private String telephone_number;
    @NonNull
    @Column(name="username")
    private String username;
    @NonNull
    @Column(name="password")
    private String password;
    @NonNull
    private String role;
    @Nullable
    private Timestamp created_at;
    @Nullable
    private Timestamp edited_at;
    @NonNull
    private boolean was_deleted;

    @JsonManagedReference(value = "user-logs")
    @OneToMany(mappedBy="user")
    @Nullable
    private Set<Log> logs;

    @JsonManagedReference(value = "user-comments")
    @OneToMany(mappedBy="user")
    @Nullable
    private Set<Comment> comments;

    @JsonManagedReference(value = "user-attachments")
    @OneToMany(mappedBy="user")
    @Nullable
    private Set<Attachment> attachments;

    @JsonManagedReference(value = "user-bugHasUsers")
    @OneToMany(mappedBy="user",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Nullable
    private Set<BugHasUser> bugHasUsers;

    @JsonManagedReference(value = "user-user_has_projects")
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Nullable
    private Set<UserHasProject> user_has_projects;

    //Constructors
    public User() {
    }

    public User(@Nullable Integer id, @NonNull String name, @NonNull String email, @Nullable String telephone_number, @NonNull String username, @NonNull String password, @NonNull String role, @Nullable Timestamp created_at, @Nullable Timestamp edited_at, boolean was_deleted, @Nullable Set<Log> logs, @Nullable Set<Comment> comments, @Nullable Set<Attachment> attachments, @Nullable Set<BugHasUser> bugHasUsers, @Nullable Set<UserHasProject> user_has_projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone_number = telephone_number;
        this.username = username;
        this.password = password;
        this.role = role;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.was_deleted = was_deleted;
        this.logs = logs;
        this.comments = comments;
        this.attachments = attachments;
        this.bugHasUsers = bugHasUsers;
        this.user_has_projects = user_has_projects;
    }

    //Getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @Nullable
    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(@Nullable String telephone_number) {
        this.telephone_number = telephone_number;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getRole() {
        return role;
    }

    public void setRole(@NonNull String role) {
        this.role = role;
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

    @Nullable
    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(@Nullable Set<Log> logs) {
        this.logs = logs;
    }

    @Nullable
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(@Nullable Set<Comment> comments) {
        this.comments = comments;
    }

    @Nullable
    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(@Nullable Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Nullable
    public Set<UserHasProject> getUser_has_projects() {
        return user_has_projects;
    }

    public void setUser_has_projects(@Nullable Set<UserHasProject> user_has_projects) {
        this.user_has_projects = user_has_projects;
    }

    @Nullable
    public Set<BugHasUser> getBugHasUsers() {
        return bugHasUsers;
    }

    public void setBugHasUsers(@Nullable Set<BugHasUser> bugHasUsers) {
        this.bugHasUsers = bugHasUsers;
    }
}
