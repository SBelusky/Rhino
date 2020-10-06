package com.samuell.rhino.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    //Atributes
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String description;
    private Timestamp created_at;
    private Timestamp edited_at;
    private boolean was_deleted;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

    @OneToMany(mappedBy="category")
    private Set<Bug> bugs;

    //Constructors
    public Category() {
    }

    public Category(int id, String name, String description, Timestamp created_at, Timestamp edited_at, boolean was_deleted, Project project, Set<Bug> bugs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.was_deleted = was_deleted;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
        Category category = (Category) o;
        return id == category.id &&
                was_deleted == category.was_deleted &&
                Objects.equals(name, category.name) &&
                Objects.equals(description, category.description) &&
                Objects.equals(created_at, category.created_at) &&
                Objects.equals(edited_at, category.edited_at) &&
                Objects.equals(project, category.project) &&
                Objects.equals(bugs, category.bugs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created_at, edited_at, was_deleted, project, bugs);
    }
}
