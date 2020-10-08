package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "specification")
public class Specification {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;
    @NonNull
    private String name;
    @Nullable
    private String description;
    @NonNull
    private String type;
    @Nullable
    private String color;
    @Nullable
    private Timestamp created_at;
    @Nullable
    private Timestamp edited_at;
    @NonNull
    private boolean was_deleted;

    @JsonManagedReference(value = "specification-bugHasSpecifications")
    @OneToMany(mappedBy="specification")
    @Nullable
    private Set<BugHasSpecification> bugHasSpecifications;

    //Constructors
    public Specification() {
    }

    public Specification(int id, String name, String description, String type, String color, Timestamp created_at, Timestamp edited_at, boolean was_deleted, Set<BugHasSpecification> bugHasSpecifications) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.color = color;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.was_deleted = was_deleted;
        this.bugHasSpecifications = bugHasSpecifications;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Set<BugHasSpecification> getBugHasSpecifications() {
        return bugHasSpecifications;
    }

    public void setBugHasSpecifications(Set<BugHasSpecification> bugHasSpecifications) {
        this.bugHasSpecifications = bugHasSpecifications;
    }
}
