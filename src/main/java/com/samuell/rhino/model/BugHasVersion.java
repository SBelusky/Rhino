package com.samuell.rhino.model;

import com.samuell.rhino.model.embedded_key.BugHasVersionKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bug_has_version")
public class BugHasVersion {
    //Atributes
    @EmbeddedId
    BugHasVersionKey id;

    private String type; // found/repared

    @ManyToOne
    @MapsId("bug_id")
    @JoinColumn(name = "bug_id")
    private Bug bug;

    @ManyToOne
    @MapsId("version_id")
    @JoinColumn(name = "version_id")
    private Version version;

    //Constructors
    public BugHasVersion() {
    }

    public BugHasVersion(BugHasVersionKey id, String type, Bug bug, Version version) {
        this.id = id;
        this.type = type;
        this.bug = bug;
        this.version = version;
    }

    //Getters & setters
    public BugHasVersionKey getId() {
        return id;
    }

    public void setId(BugHasVersionKey id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugHasVersion that = (BugHasVersion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(bug, that.bug) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, bug, version);
    }
}
