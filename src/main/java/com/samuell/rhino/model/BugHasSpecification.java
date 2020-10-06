package com.samuell.rhino.model;

import com.samuell.rhino.model.embedded_key.BugHasVersionKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bug_has_specification")
public class BugHasSpecification {
    //Atributes
    @EmbeddedId
    private BugHasVersionKey id;

    @ManyToOne
    @MapsId("bug_id")
    @JoinColumn(name = "bug_id")
    private Bug bug;

    @ManyToOne
    @MapsId("specification_id")
    @JoinColumn(name = "specification_id")
    private Specification specification;

    //Constructors
    public BugHasSpecification() {
    }

    public BugHasSpecification(BugHasVersionKey id, Bug bug, Specification specification) {
        this.id = id;
        this.bug = bug;
        this.specification = specification;
    }

    //Getters & setters
    public BugHasVersionKey getId() {
        return id;
    }

    public void setId(BugHasVersionKey id) {
        this.id = id;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugHasSpecification that = (BugHasSpecification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bug, that.bug) &&
                Objects.equals(specification, that.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bug, specification);
    }
}
