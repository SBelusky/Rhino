package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "bug")
public class Bug {
    //Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;
    @NonNull
    private String summarize;
    @NonNull
    private String description;
    @Nullable
    private String additional_info;
    @Nullable
    private Integer seek_time;
    @Nullable
    private Timestamp created_at;
    @Nullable
    private Timestamp edited_at;
    @NonNull
    private boolean was_deleted;

    @JsonManagedReference(value = "bug-attachments")
    @OneToMany(mappedBy="bug")
    @Nullable
    private Set<Attachment> attachments;

    @JsonManagedReference(value = "bug-logs")
    @OneToMany(mappedBy="bug")
    @Nullable
    private Set<Log> logs;

    @JsonManagedReference(value = "bug-comments")
    @OneToMany(mappedBy="bug")
    @Nullable
    private Set<Comment> comments;

    @JsonManagedReference(value = "bug-bugHasVersions")
    @OneToMany(mappedBy="bug")
    @Nullable
    private Set<BugHasVersion> bugHasVersions;

    @JsonManagedReference(value = "bug-bugHasSpecifications")
    @OneToMany(mappedBy="bug")
    @Nullable
    private Set<BugHasSpecification> bugHasSpecifications;

    @JsonManagedReference(value = "bug-bugHasBugsContains")
    @OneToMany(mappedBy="contains")
    @Nullable
    private Set<BugHasBug> bugHasBugsContains; //bug contains bugs

    @JsonManagedReference(value = "bug-bugHasBugsIncluded")
    @OneToMany(mappedBy="included")
    @Nullable
    private Set<BugHasBug> bugHasBugsIncluded;// bug is included in bugs

    @JsonBackReference(value = "category-bugs")
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    @NonNull
    private Category category;

    @JsonBackReference(value = "project-bugs")
    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    @NonNull
    private Project project;

    @JsonBackReference(value = "user-bugs")
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @NonNull
    private User user;

    //Constructors
    public Bug() {
    }

    public Bug(@Nullable Integer id, @NonNull String summarize, @NonNull String description, @Nullable String additional_info, @Nullable Integer seek_time, @Nullable Timestamp created_at, @Nullable Timestamp edited_at, boolean was_deleted, @Nullable Set<Attachment> attachments, @Nullable Set<Log> logs, @Nullable Set<Comment> comments, @Nullable Set<BugHasVersion> bugHasVersions, @Nullable Set<BugHasSpecification> bugHasSpecifications, @Nullable Set<BugHasBug> bugHasBugsContains, @Nullable Set<BugHasBug> bugHasBugsIncluded, @NonNull Category category, @NonNull Project project) {
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
    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @NonNull
    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(@NonNull String summarize) {
        this.summarize = summarize;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Nullable
    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(@Nullable String additional_info) {
        this.additional_info = additional_info;
    }

    @Nullable
    public Integer getSeek_time() {
        return seek_time;
    }

    public void setSeek_time(@Nullable Integer seek_time) {
        this.seek_time = seek_time;
    }

    @Nullable
    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(@Nullable Timestamp created_at) {
        this.created_at = created_at;
    }

    @Nullable
    public Timestamp getEdited_at() {
        return edited_at;
    }

    public void setEdited_at(@Nullable Timestamp edited_at) {
        this.edited_at = edited_at;
    }

    public boolean isWas_deleted() {
        return was_deleted;
    }

    public void setWas_deleted(boolean was_deleted) {
        this.was_deleted = was_deleted;
    }

    @Nullable
    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(@Nullable Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Nullable
    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(@Nullable Set<Log> logs) {
        this.logs = logs;
    }

    @Nullable
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(@Nullable Set<Comment> comments) {
        this.comments = comments;
    }

    @Nullable
    public Set<BugHasVersion> getBugHasVersions() {
        return bugHasVersions;
    }

    public void setBugHasVersions(@Nullable Set<BugHasVersion> bugHasVersions) {
        this.bugHasVersions = bugHasVersions;
    }

    @Nullable
    public Set<BugHasSpecification> getBugHasSpecifications() {
        return bugHasSpecifications;
    }

    public void setBugHasSpecifications(@Nullable Set<BugHasSpecification> bugHasSpecifications) {
        this.bugHasSpecifications = bugHasSpecifications;
    }

    @Nullable
    public Set<BugHasBug> getBugHasBugsContains() {
        return bugHasBugsContains;
    }

    public void setBugHasBugsContains(@Nullable Set<BugHasBug> bugHasBugsContains) {
        this.bugHasBugsContains = bugHasBugsContains;
    }

    @Nullable
    public Set<BugHasBug> getBugHasBugsIncluded() {
        return bugHasBugsIncluded;
    }

    public void setBugHasBugsIncluded(@Nullable Set<BugHasBug> bugHasBugsIncluded) {
        this.bugHasBugsIncluded = bugHasBugsIncluded;
    }

    @NonNull
    public Category getCategory() {
        return category;
    }

    public void setCategory(@NonNull Category category) {
        this.category = category;
    }

    @NonNull
    public Project getProject() {
        return project;
    }

    public void setProject(@NonNull Project project) {
        this.project = project;
    }

    @NonNull
    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }
}
