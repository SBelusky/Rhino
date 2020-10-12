package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Set;

public class BugDto {
    //Atributes
    private Integer id;
    private String summarize;
    private String description;
    private String additional_info;
    private Integer seek_time;
    private Set<BugHasVersion> bugHasVersions;
    private Set<BugHasSpecification> bugHasSpecifications;
    private ProjectDto project;
    private CategoryDto category;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;

    //Constructors
    public BugDto() {
    }

    public BugDto(Integer id, String summarize, String description, String additional_info, Integer seek_time, Set<BugHasVersion> bugHasVersions, Set<BugHasSpecification> bugHasSpecifications, ProjectDto project, CategoryDto category, Timestamp created_at) {
        this.id = id;
        this.summarize = summarize;
        this.description = description;
        this.additional_info = additional_info;
        this.seek_time = seek_time;
        this.bugHasVersions = bugHasVersions;
        this.bugHasSpecifications = bugHasSpecifications;
        this.project = project;
        this.category = category;
        this.created_at = created_at;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public Integer getSeek_time() {
        return seek_time;
    }

    public void setSeek_time(Integer seek_time) {
        this.seek_time = seek_time;
    }

    public Set<BugHasVersion> getBugHasVersions() {
        return bugHasVersions;
    }

    public void setBugHasVersions(Set<BugHasVersion> bugHasVersions) {
        this.bugHasVersions = bugHasVersions;
    }

    public Set<BugHasSpecification> getBugHasSpecifications() {
        return bugHasSpecifications;
    }

    public void setBugHasSpecifications(Set<BugHasSpecification> bugHasSpecifications) {
        this.bugHasSpecifications = bugHasSpecifications;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
