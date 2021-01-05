package com.samuell.rhino.model.embedded_key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BugHasUserKey implements Serializable {
    //Atributes
    @Column(name = "type")
    private String type;

    @Column(name = "bug_id")
    private Integer bug_id;

    @Column(name = "user_id")
    private Integer user_id;

    //Constructors
    public BugHasUserKey() {
    }

    public BugHasUserKey(String type, Integer bug_id, Integer user_id) {
        this.type = type;
        this.bug_id = bug_id;
        this.user_id = user_id;
    }

    //Getters & setters
    public Integer getBug_id() {
        return bug_id;
    }

    public void setBug_id(Integer bug_id) {
        this.bug_id = bug_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugHasUserKey that = (BugHasUserKey) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(bug_id, that.bug_id) &&
                Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, bug_id, user_id);
    }
}
