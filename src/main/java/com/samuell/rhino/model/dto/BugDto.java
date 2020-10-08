package com.samuell.rhino.model.dto;

import java.sql.Timestamp;
import java.util.Set;

public class BugDto {
    //Atributes
    private Integer id;
    private String summarize;
    private String description;
    private String additional_info;
    private Integer seek_time;
    private Set<BugHasVersionDto> bugHasVersions;
    private Set<BugHasSpecificationDto> bugHasSpecifications;
    private ProjectDto project;
    private CategoryDto category;
    private Timestamp created_at;

    //Constructors
    public BugDto() {
    }

    public BugDto(Integer id, String summarize, String description, String additional_info, Integer seek_time, Set<BugHasVersionDto> bugHasVersions, Set<BugHasSpecificationDto> bugHasSpecifications, ProjectDto project, CategoryDto category, Timestamp created_at) {
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

    public Set<BugHasVersionDto> getBugHasVersions() {
        return bugHasVersions;
    }

    public void setBugHasVersions(Set<BugHasVersionDto> bugHasVersions) {
        this.bugHasVersions = bugHasVersions;
    }

    public Set<BugHasSpecificationDto> getBugHasSpecifications() {
        return bugHasSpecifications;
    }

    public void setBugHasSpecifications(Set<BugHasSpecificationDto> bugHasSpecifications) {
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
