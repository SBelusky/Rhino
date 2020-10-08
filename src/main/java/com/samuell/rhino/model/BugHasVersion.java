package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.embedded_key.BugHasVersionKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bug_has_version")
public class BugHasVersion {
    //Attributes
    @EmbeddedId
    private BugHasVersionKey id;

    private String type; // found/repared

    @JsonBackReference(value = "bug-bugHasVersions")
    @ManyToOne
    @MapsId("bug_id")
    @JoinColumn(name = "bug_id")
    private Bug bug;

    @JsonBackReference(value = "version-bugHasVersions")
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
}
