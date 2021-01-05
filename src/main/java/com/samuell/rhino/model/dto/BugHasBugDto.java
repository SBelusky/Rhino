package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.samuell.rhino.model.embedded_key.BugHasBugKey;

public class BugHasBugDto {
    //Attributes
    private BugHasBugKey id;
    private String status;

    private BugDto contains;

    private BugDto included;

    //Constructors
    public BugHasBugDto() {
    }

    public BugHasBugDto(BugHasBugKey id, BugDto contains, BugDto included, String status) {
        this.id = id;
        this.contains = contains;
        this.included = included;
        this.status = status;
    }

    //Getters & setters

    public BugHasBugKey getId() {
        return id;
    }

    public void setId(BugHasBugKey id) {
        this.id = id;
    }

    public BugDto getContains() {
        return contains;
    }

    public void setContains(BugDto contains) {
        this.contains = contains;
    }

    public BugDto getIncluded() {
        return included;
    }

    public void setIncluded(BugDto included) {
        this.included = included;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
