package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.embedded_key.BugHasVersionKey;

public class BugHasVersionDto {
    //Attributes
    private BugHasVersionKey id;
    private VersionDto version;
    private String type;

    @JsonBackReference
    private BugDto bug;

    //Constructors
    public BugHasVersionDto() {
    }

    public BugHasVersionDto(BugHasVersionKey id, BugDto bug, VersionDto version) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
