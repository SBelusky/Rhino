package com.samuell.rhino.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    //Atributes
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String email;
    private String telephone_number;
    private String login_name;
    private String login_password;
    private String role;
    private Timestamp created_at;
    private Timestamp edited_at;
    private boolean was_deleted;

    @OneToMany(mappedBy="user")
    private Set<Log> logs;

    @OneToMany(mappedBy="user")
    private Set<Comment> comments;

    @OneToMany(mappedBy="user")
    private Set<Attachment> attachments;

    @OneToMany(mappedBy = "user")
    private Set<UserHasProject> user_has_projects;

    //Constructors
    public User() {
    }

    public User(int id, String name, String email, String telephone_number, String login_name, String login_password, String role, Timestamp created_at, Timestamp edited_at, boolean was_deleted, Set<Log> logs, Set<Comment> comments, Set<Attachment> attachments, Set<UserHasProject> user_has_projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone_number = telephone_number;
        this.login_name = login_name;
        this.login_password = login_password;
        this.role = role;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.was_deleted = was_deleted;
        this.logs = logs;
        this.comments = comments;
        this.attachments = attachments;
        this.user_has_projects = user_has_projects;
    }

    //Getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Set<UserHasProject> getUser_has_projects() {
        return user_has_projects;
    }

    public void setUser_has_projects(Set<UserHasProject> user_has_projects) {
        this.user_has_projects = user_has_projects;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                was_deleted == user.was_deleted &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(telephone_number, user.telephone_number) &&
                Objects.equals(login_name, user.login_name) &&
                Objects.equals(login_password, user.login_password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(created_at, user.created_at) &&
                Objects.equals(edited_at, user.edited_at) &&
                Objects.equals(logs, user.logs) &&
                Objects.equals(comments, user.comments) &&
                Objects.equals(attachments, user.attachments) &&
                Objects.equals(user_has_projects, user.user_has_projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, telephone_number, login_name, login_password, role, created_at, edited_at, was_deleted, logs, comments, attachments, user_has_projects);
    }
}
