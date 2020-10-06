package com.samuell.rhino.model.embedded_key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserHasProjectKey implements Serializable {
    @Column(name = "user_id")
    int user_id;

    @Column(name = "project_id")
    int project_id;

    //Constructors
    public UserHasProjectKey() {
    }

    public UserHasProjectKey(int user_id, int project_id) {
        this.user_id = user_id;
        this.project_id = project_id;
    }

    //Getters & setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasProjectKey that = (UserHasProjectKey) o;
        return user_id == that.user_id &&
                project_id == that.project_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, project_id);
    }
}
