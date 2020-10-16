package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Set;

public class BugContainsDto {
    //Atributes
    private Integer id;
    private String summarize;
    private Set<BugHasSpecificationDto> bugHasSpecifications;
    private CategoryDto category;
    private UserDto user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp edited_at;

    //Constructors
    public BugContainsDto() {
    }

    public BugContainsDto(Integer id, String summarize, Set<BugHasSpecificationDto> bugHasSpecifications, CategoryDto category, UserDto user, Timestamp created_at, Timestamp edited_at) {
        this.id = id;
        this.summarize = summarize;
        this.bugHasSpecifications = bugHasSpecifications;
        this.category = category;
        this.user = user;
        this.created_at = created_at;
        this.edited_at = edited_at;
    }

    //Getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public Set<BugHasSpecificationDto> getBugHasSpecifications() {
        return bugHasSpecifications;
    }

    public void setBugHasSpecifications(Set<BugHasSpecificationDto> bugHasSpecifications) {
        this.bugHasSpecifications = bugHasSpecifications;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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
}