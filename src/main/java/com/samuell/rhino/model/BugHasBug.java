package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.embedded_key.BugHasBugKey;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bug_has_bug")
public class BugHasBug {
    //Atributes
    @EmbeddedId
    private BugHasBugKey id;
    @NonNull
    private String status;

    @JsonBackReference(value = "bug-bugHasBugsContains")
    @ManyToOne
    @MapsId("contains")
    @JoinColumn(name = "bug_id1")
    @NonNull
    private Bug contains;

    @JsonBackReference(value = "bug-bugHasBugsIncluded")
    @ManyToOne
    @MapsId("included")
    @JoinColumn(name = "bug_id2")
    @NonNull
    private Bug included;

    //Constructors
    public BugHasBug() {
    }

    public BugHasBug(BugHasBugKey id, String status, Bug contains, Bug included) {
        this.id = id;
        this.status = status;
        this.contains = contains;
        this.included = included;
    }

    //Getters & setters
    public BugHasBugKey getId() {
        return id;
    }

    public void setId(BugHasBugKey id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bug getContains() {
        return contains;
    }

    public void setContains(Bug contains) {
        this.contains = contains;
    }

    public Bug getIncluded() {
        return included;
    }

    public void setIncluded(Bug included) {
        this.included = included;
    }
}
