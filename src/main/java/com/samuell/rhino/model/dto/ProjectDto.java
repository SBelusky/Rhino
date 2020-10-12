package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.Set;

public class ProjectDto {
    //Attributes
    private Integer id;
    private String name;
    private String description;
    @JsonManagedReference
    private Set<UserHasProjectDto> user_has_projects;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;


    //Constructors
    public ProjectDto() {
    }

    public ProjectDto(Integer id, String name, String description, Set<UserHasProjectDto> user_has_projects) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user_has_projects = user_has_projects;
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
