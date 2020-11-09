package com.samuell.rhino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.embedded_key.BugHasSpecificationKey;
import com.samuell.rhino.model.embedded_key.BugHasVersionKey;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "bug_has_specification")
@Table(name = "bug_has_specification")
public class BugHasSpecification {
    //Attributes
    @EmbeddedId
    private BugHasSpecificationKey id;

    @JsonBackReference(value = "bug-bugHasSpecifications")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("bug_id")
    @JoinColumn(name = "bug_id")
    @NonNull
    private Bug bug;

    @JsonBackReference(value = "specification-bugHasSpecifications")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("specification_id")
    @JoinColumn(name = "specification_id")
    @NonNull
    private Specification specification;

    //Constructors
    public BugHasSpecification() {
    }

    public BugHasSpecification(BugHasSpecificationKey id, Bug bug, Specification specification) {
        this.id = id;
        this.bug = bug;
        this.specification = specification;
    }

    //Getters & setters
    public BugHasSpecificationKey getId() {
        return id;
    }

    public void setId(BugHasSpecificationKey id) {
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
}
