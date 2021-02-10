package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.samuell.rhino.model.embedded_key.BugHasBugKey;

import java.util.Set;

public class BugHasBugDto {
    //Attributes
    private BugHasBugKey id;
    private String status;
    private Integer idOfLastEditingUser;
    private String summarize;
    private String category;
    private Set<BugHasSpecificationDto> state;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<BugHasSpecificationDto> getState() {
        return state;
    }

    public void setState(Set<BugHasSpecificationDto> state) {
        this.state = state;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

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

    public Integer getIdOfLastEditingUser() {
        return idOfLastEditingUser;
    }

    public void setIdOfLastEditingUser(Integer idOfLastEditingUser) {
        this.idOfLastEditingUser = idOfLastEditingUser;
    }
}
