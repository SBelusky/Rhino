package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;

public class VersionDto {
    //Attributes
    private Integer id;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;
    private ProjectDto project;

    //Constructors
    public VersionDto() {
    }

    public VersionDto(Integer id, String name, String description, Timestamp created_at, ProjectDto project) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.project = project;
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

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }
}
