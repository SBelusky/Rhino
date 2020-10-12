package com.samuell.rhino.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class AttachmentDto {
    //Attributes
    private Integer id;
    private String file;
    private String name;
    private UserDto user;
    private BugDto bug;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;


    //Constructors
    public AttachmentDto() {
    }

    public AttachmentDto(Integer id, String file, String name, UserDto user, BugDto bug, Timestamp created_at) {
        this.id = id;
        this.file = file;
        this.name = name;
        this.user = user;
        this.bug = bug;
        this.created_at = created_at;
    }

    //Getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
