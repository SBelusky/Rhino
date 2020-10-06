package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "bug")
public class Bug {
    //Atributes
    @Id
    @GeneratedValue
    private int id;

    private String summarize;
    private String description;
    private String additional_info;
    private int seek_time;
    private Timestamp created_at;
    private Timestamp edited_at;
    private boolean was_deleted;

    @JsonManagedReference
    @OneToMany(mappedBy="bug")
    private Set<Attachment> attachments;

    @JsonManagedReference
    @OneToMany(mappedBy="bug")
    private Set<Log> logs;

    @JsonManagedReference
    @OneToMany(mappedBy="bug")
    private Set<Comment> comments;

    @JsonManagedReference
    @OneToMany(mappedBy="bug")
    private Set<BugHasVersion> bugHasVersions;

    @JsonManagedReference
    @OneToMany(mappedBy="bug")
    private Set<BugHasSpecification> bugHasSpecifications;

    @JsonManagedReference
    @OneToMany(mappedBy="contains")
    private Set<BugHasBug> bugHasBugsContains; //bug contains bugs

    @JsonManagedReference
    @OneToMany(mappedBy="included")
    private Set<BugHasBug> bugHasBugsIncluded;// bug is included in bugs

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

    //Constructors
    public Bug() {
    }

    public Bug(int id, String summarize, String description, String additional_info, int seek_time, Timestamp created_at, Timestamp edited_at, boolean was_deleted, Set<Attachment> attachments, Set<Log> logs, Set<Comment> comments, Set<BugHasVersion> bugHasVersions, Set<BugHasSpecification> bugHasSpecifications, Set<BugHasBug> bugHasBugsContains, Set<BugHasBug> bugHasBugsIncluded, Category category, Project project) {
        this.id = id;
        this.summarize = summarize;
        this.description = description;
        this.additional_info = additional_info;
        this.seek_time = seek_time;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.was_deleted = was_deleted;
        this.attachments = attachments;
        this.logs = logs;
        this.comments = comments;
        this.bugHasVersions = bugHasVersions;
        this.bugHasSpecifications = bugHasSpecifications;
        this.bugHasBugsContains = bugHasBugsContains;
        this.bugHasBugsIncluded = bugHasBugsIncluded;
        this.category = category;
        this.project = project;
    }

    //Getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getSeek_time() {
        return seek_time;
    }

    public void setSeek_time(int seek_time) {
        this.seek_time = seek_time;
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

    public boolean isWas_deleted() {
        return was_deleted;
    }

    public void setWas_deleted(boolean was_deleted) {
        this.was_deleted = was_deleted;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    public Set<BugHasBug> getBugHasBugsContains() {
        return bugHasBugsContains;
    }

    public void setBugHasBugsContains(Set<BugHasBug> bugHasBugsContains) {
        this.bugHasBugsContains = bugHasBugsContains;
    }

    public Set<BugHasBug> getBugHasBugsIncluded() {
        return bugHasBugsIncluded;
    }

    public void setBugHasBugsIncluded(Set<BugHasBug> bugHasBugsIncluded) {
        this.bugHasBugsIncluded = bugHasBugsIncluded;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
