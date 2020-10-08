package com.samuell.rhino.model.dto;

import java.sql.Timestamp;
import java.util.Set;

public class ProjectDto {
    //Attributes
    private Integer id;
    private String name;
    private String description;
    private Set<UserHasProjectDto> user_has_projects;
    private Timestamp created_at;


    //Constructors
    public ProjectDto() {
    }

    public ProjectDto(Integer id, String name, String description, Set<UserHasProjectDto> user_has_projects, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user_has_projects = user_has_projects;
        this.created_at = created_at;
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

    public Set<UserHasProjectDto> getUser_has_projects() {
        return user_has_projects;
    }

    public void setUser_has_projects(Set<UserHasProjectDto> user_has_projects) {
        this.user_has_projects = user_has_projects;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
