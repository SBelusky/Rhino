package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Objects;
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
    private UserDto user;
    private Integer idOfLastEditingUser;
    private Set<BugHasBugDto> bugHasBugsContains;
    private Set<BugHasBugDto> bugHasBugsIncluded;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp edited_at;

    //Constructors
    public BugDto() {
    }

    //Getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<BugHasBugDto> getBugHasBugsContains() {
        return bugHasBugsContains;
    }

    public void setBugHasBugsContains(Set<BugHasBugDto> bugHasBugsContains) {
        this.bugHasBugsContains = bugHasBugsContains;
    }

    public Set<BugHasBugDto> getBugHasBugsIncluded() {
        return bugHasBugsIncluded;
    }

    public void setBugHasBugsIncluded(Set<BugHasBugDto> bugHasBugsIncluded) {
        this.bugHasBugsIncluded = bugHasBugsIncluded;
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

    public Integer getIdOfLastEditingUser() {
        return idOfLastEditingUser;
    }

    public void setIdOfLastEditingUser(Integer idOfLastEditingUser) {
        this.idOfLastEditingUser = idOfLastEditingUser;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugDto bugDto = (BugDto) o;
        return Objects.equals(id, bugDto.id) &&
                Objects.equals(summarize, bugDto.summarize) &&
                Objects.equals(description, bugDto.description) &&
                Objects.equals(additional_info, bugDto.additional_info) &&
                Objects.equals(seek_time, bugDto.seek_time) &&
                Objects.equals(bugHasVersions, bugDto.bugHasVersions) &&
                Objects.equals(bugHasSpecifications, bugDto.bugHasSpecifications) &&
                Objects.equals(project, bugDto.project) &&
                Objects.equals(category, bugDto.category) &&
                Objects.equals(user, bugDto.user) &&
                Objects.equals(idOfLastEditingUser, bugDto.idOfLastEditingUser) &&
                Objects.equals(bugHasBugsContains, bugDto.bugHasBugsContains) &&
                Objects.equals(bugHasBugsIncluded, bugDto.bugHasBugsIncluded) &&
                Objects.equals(created_at, bugDto.created_at) &&
                Objects.equals(edited_at, bugDto.edited_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summarize, description, additional_info, seek_time, bugHasVersions, bugHasSpecifications, project, category, user, idOfLastEditingUser, bugHasBugsContains, bugHasBugsIncluded, created_at, edited_at);
    }
}
