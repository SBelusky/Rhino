package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    //Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;
    @NonNull
    private String name = "";
    @Nullable
    private String description;
    @Nullable
    private Timestamp created_at;
    @Nullable
    private Timestamp edited_at;
    @NonNull
    private boolean was_deleted = false;

    @JsonBackReference(value = "project-categories")
    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    @NonNull
    private Project project = new Project();

    @JsonManagedReference(value = "category-bugs")
    @OneToMany(mappedBy="category")
    @Nullable
    private Set<Bug> bugs;

    //Constructors
    public Category() {
    }

    public Category(Integer id, @NonNull String name, @Nullable String description, @Nullable Timestamp created_at, @Nullable Timestamp edited_at, boolean was_deleted, @NonNull Project project, @Nullable Set<Bug> bugs) {
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
    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
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
    public Project getProject() {
        return project;
    }

    public void setProject(@NonNull Project project) {
        this.project = project;
    }

    @Nullable
    public Set<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(@Nullable Set<Bug> bugs) {
        this.bugs = bugs;
    }
}
