package com.samuell.rhino.model.dto;

import com.samuell.rhino.model.embedded_key.BugHasVersionKey;

public class BugHasVersion {
    //Attributes
    private BugHasVersionKey id;
    private BugDto bug;
    private VersionDto version;

    //Constructors
    public BugHasVersion() {
    }

    public BugHasVersion(BugHasVersionKey id, BugDto bug, VersionDto version) {
        this.id = id;
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

    public BugDto getBug() {
        return bug;
    }

    public void setBug(BugDto bug) {
        this.bug = bug;
    }

    public VersionDto getVersion() {
        return version;
    }

    public void setVersion(VersionDto version) {
        this.version = version;
    }
}
