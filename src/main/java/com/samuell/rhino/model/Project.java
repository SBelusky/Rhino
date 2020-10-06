package com.samuell.rhino.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    //Attributes
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String description;
    private Timestamp created_at;
    private Timestamp edited_at;
    private boolean was_deleted;

    @OneToMany(mappedBy = "project")
    Set<Version> versions;

    @OneToMany(mappedBy = "project")
    Set<UserHasProject> user_has_projects;

    @OneToMany(mappedBy = "project")
    Set<Category> categories;

    @OneToMany(mappedBy = "project")
    Set<Bug> bugs;

    //Constructors
    public Project() {
    }

    public Project(int id, String name, String description, Timestamp created_at, Timestamp edited_at, boolean was_deleted, Set<Version> versions, Set<UserHasProject> user_has_projects, Set<Category> categories, Set<Bug> bugs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.was_deleted = was_deleted;
        this.versions = versions;
        this.user_has_projects = user_has_projects;
        this.categories = categories;
        this.bugs = bugs;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<Version> getVersions() {
        return versions;
    }

    public void setVersions(Set<Version> versions) {
        this.versions = versions;
    }

    public Set<UserHasProject> getUser_has_projects() {
        return user_has_projects;
    }

    public void setUser_has_projects(Set<UserHasProject> user_has_projects) {
        this.user_has_projects = user_has_projects;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(Set<Bug> bugs) {
        this.bugs = bugs;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                was_deleted == project.was_deleted &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(created_at, project.created_at) &&
                Objects.equals(edited_at, project.edited_at) &&
                Objects.equals(versions, project.versions) &&
                Objects.equals(user_has_projects, project.user_has_projects) &&
                Objects.equals(categories, project.categories) &&
                Objects.equals(bugs, project.bugs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created_at, edited_at, was_deleted, versions, user_has_projects, categories, bugs);
    }
}
