package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class LogDto {
    //Attributes
    private Integer id;
    private String message;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;
    private UserDto user;
    private BugDto bug;

    //Constructors
    public LogDto() {
    }

    public LogDto(Integer id, String message, String status, Timestamp created_at, UserDto user, BugDto bug) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.created_at = created_at;
        this.user = user;
        this.bug = bug;
    }

    //Getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public BugDto getBug() {
        return bug;
    }

    public void setBug(BugDto bug) {
        this.bug = bug;
    }
}
