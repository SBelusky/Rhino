package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.embedded_key.BugHasUserKey;

public class BugHasUserDto {
    //Attributes
    private BugHasUserKey id;
    private UserDto user;
    private String type;

    @JsonBackReference
    private BugDto bug;

    //Constructors
    public BugHasUserDto() {
    }

    public BugHasUserDto(BugHasUserKey id, UserDto user, String type, BugDto bug) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.bug = bug;
    }

    //Getters & setters
    public BugHasUserKey getId() {
        return id;
    }

    public void setId(BugHasUserKey id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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
}
