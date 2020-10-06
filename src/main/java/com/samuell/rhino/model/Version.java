package com.samuell.rhino.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "version")
public class Version {
    //Attributes
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String description;
    private Timestamp created_at;
    private Timestamp edited_at;
    private boolean was_deleted;

    @OneToMany(mappedBy="version")
    private Set<BugHasVersion> bug_has_versions;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

    //Constructors
    public Version() {
    }

    public Version(int id, String name, String description, Timestamp created_at, Timestamp edited_at, boolean was_deleted, Set<BugHasVersion> bug_has_versions, Project project) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.was_deleted = was_deleted;
        this.bug_has_versions = bug_has_versions;
        this.project = project;
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

    public Set<BugHasVersion> getBug_has_versions() {
        return bug_has_versions;
    }

    public void setBug_has_versions(Set<BugHasVersion> bug_has_versions) {
        this.bug_has_versions = bug_has_versions;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return id == version.id &&
                was_deleted == version.was_deleted &&
                Objects.equals(name, version.name) &&
                Objects.equals(description, version.description) &&
                Objects.equals(created_at, version.created_at) &&
                Objects.equals(edited_at, version.edited_at) &&
                Objects.equals(bug_has_versions, version.bug_has_versions) &&
                Objects.equals(project, version.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created_at, edited_at, was_deleted, bug_has_versions, project);
    }
}
