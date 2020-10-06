package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.embedded_key.UserHasProjectKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_has_project")
public class UserHasProject {
    @EmbeddedId
    private UserHasProjectKey id;

    @JsonBackReference
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne
    @MapsId("project_id")
    @JoinColumn(name = "project_id")
    private Project project;

    //Constructors
    public UserHasProject() {
    }

    public UserHasProject(UserHasProjectKey id, User user, Project project) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
