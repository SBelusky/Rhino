package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.Bug;
import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.embedded_key.BugHasVersionKey;

public class BugHasSpecificationDto {
    //Attributes
    private BugHasVersionKey id;
    private SpecificationDto specification;

    @JsonBackReference
    private BugDto bug;

    //Constructors
    public BugHasSpecificationDto() {
    }

    public BugHasSpecificationDto(BugHasVersionKey id, BugDto bug, SpecificationDto specification) {
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

    public BugDto getBug() {
        return bug;
    }

    public void setBug(BugDto bug) {
        this.bug = bug;
    }

    public SpecificationDto getSpecification() {
        return specification;
    }

    public void setSpecification(SpecificationDto specification) {
        this.specification = specification;
    }
}
