package com.samuell.rhino.model;

import com.samuell.rhino.model.embedded_key.BugHasBugKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bug_has_bug")
public class BugHasBug {
    //Atributes
    @EmbeddedId
    BugHasBugKey id;

    private String status;

    @ManyToOne
    @MapsId("contains")
    @JoinColumn(name = "bug_id1")
    private Bug contains;

    @ManyToOne
    @MapsId("included")
    @JoinColumn(name = "bug_id2")
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

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugHasBug bugHasBug = (BugHasBug) o;
        return Objects.equals(id, bugHasBug.id) &&
                Objects.equals(status, bugHasBug.status) &&
                Objects.equals(contains, bugHasBug.contains) &&
                Objects.equals(included, bugHasBug.included);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, contains, included);
    }
}
