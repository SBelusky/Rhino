package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;
    @NonNull
    private String name;
    @Nullable
    private String description;
    @Nullable
    private Timestamp created_at;
    @Nullable
    private Timestamp edited_at;
    @NonNull
    private boolean was_deleted;

    @JsonManagedReference(value = "project-versions")
    @OneToMany(mappedBy = "project")
    @Nullable
    private Set<Version> versions;

    @JsonManagedReference(value = "project-user_has_projects")
    @OneToMany(mappedBy = "project",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Nullable
    private Set<UserHasProject> user_has_projects;

    @JsonManagedReference(value = "project-categories")
    @OneToMany(mappedBy = "project")
    @Nullable
    private Set<Category> categories;

    @JsonManagedReference(value = "project-bugs")
    @OneToMany(mappedBy = "project")
    @Nullable
    private Set<Bug> bugs;

    //Constructors
    public Project() {
    }

    public Project(Integer id, String name, String description, Timestamp created_at, Timestamp edited_at, boolean was_deleted, Set<Version> versions, Set<UserHasProject> user_has_projects, Set<Category> categories, Set<Bug> bugs) {
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

    public void deleleteUsers(){
        this.user_has_projects = new HashSet<>();
    }
    //Getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
