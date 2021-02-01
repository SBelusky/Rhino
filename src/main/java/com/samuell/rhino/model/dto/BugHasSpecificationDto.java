package com.samuell.rhino.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samuell.rhino.model.Bug;
import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.embedded_key.BugHasSpecificationKey;
import com.samuell.rhino.model.embedded_key.BugHasVersionKey;

public class BugHasSpecificationDto {
    //Attributes
    private BugHasSpecificationKey id;
    private SpecificationDto specification;
    private String type;

    @JsonBackReference
    private BugDto bug;

    //Constructors
    public BugHasSpecificationDto() {
    }

    public BugHasSpecificationDto(BugHasSpecificationKey id, BugDto bug, SpecificationDto specification) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
