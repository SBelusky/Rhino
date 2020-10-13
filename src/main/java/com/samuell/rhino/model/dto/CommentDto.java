package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class CommentDto {
    //Attributes
    private Integer id;
    private String description;
    private Integer spend_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp edited_at;
    private UserDto user;

    //Constructors
    public CommentDto() {
    }

    public CommentDto(Integer id, String description, Integer spend_time, Timestamp created_at, Timestamp edited_at, UserDto user) {
        this.id = id;
        this.description = description;
        this.spend_time = spend_time;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.user = user;
    }

    //Getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSpend_time() {
        return spend_time;
    }

    public void setSpend_time(Integer spend_time) {
        this.spend_time = spend_time;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getEdited_at() {
        return edited_at;
    }

    public void setEdited_at(Timestamp edited_at) {
        this.edited_at = edited_at;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
