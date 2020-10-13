package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.embedded_key.UserHasProjectKey;

public class UserHasProjectDto {
    //Attributes
    private UserHasProjectKey id;
    private UserDto user;
    @JsonBackReference
    private ProjectDto project;

    //Constructors
    public UserHasProjectDto() {
    }

    public UserHasProjectDto(UserHasProjectKey id, UserDto user, ProjectDto project) {
        this.id = id;
        this.user = user;
        this.project = project;
    }

    //Getters & setters
    public UserHasProjectKey getId() {
        return id;
    }

    public void setId(UserHasProjectKey id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }
}
