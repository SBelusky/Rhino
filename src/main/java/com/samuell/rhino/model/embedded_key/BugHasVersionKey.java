package com.samuell.rhino.model.embedded_key;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class BugHasVersionKey implements Serializable{
    //Atributes
    @Column(name = "type")
    private String type;

    @Column(name = "bug_id")
    private Integer bug_id;

    @Column(name = "version_id")
    private Integer version_id;

    //Constructors
    public BugHasVersionKey() {
    }

    public BugHasVersionKey(String type, Integer bug_id, Integer version_id) {
        this.type = type;
        this.bug_id = bug_id;
        this.version_id = version_id;
    }

    //Getters & setters

    public void setBug_id(Integer bug_id) {
        this.bug_id = bug_id;
    }

    public void setVersion_id(Integer version_id) {
        this.version_id = version_id;
    }

    public Integer getBug_id() {
        return bug_id;
    }

    public Integer getVersion_id() {
        return version_id;
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
        BugHasVersionKey that = (BugHasVersionKey) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(bug_id, that.bug_id) &&
                Objects.equals(version_id, that.version_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, bug_id, version_id);
    }
}
