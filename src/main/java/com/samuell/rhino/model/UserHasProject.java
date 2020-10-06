package com.samuell.rhino.model;

import com.samuell.rhino.model.embedded_key.UserHasProjectKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_has_project")
public class UserHasProject {
    @EmbeddedId
    UserHasProjectKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

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

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasProject that = (UserHasProject) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, project);
    }
}
