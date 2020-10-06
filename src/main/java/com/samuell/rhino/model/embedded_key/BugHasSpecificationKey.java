package com.samuell.rhino.model.embedded_key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BugHasSpecificationKey implements Serializable {
    //Atributes
    @Column(name = "bug_id")
    private int bug_id;

    @Column(name = "specification_id")
    private int specification_id;

    //Constructors
    public BugHasSpecificationKey() {
    }

    public BugHasSpecificationKey(int bug_id, int specification_id) {
        this.bug_id = bug_id;
        this.specification_id = specification_id;
    }

    //Getters & setters
    public int getBug_id() {
        return bug_id;
    }

    public void setBug_id(int bug_id) {
        this.bug_id = bug_id;
    }

    public int getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(int specification_id) {
        this.specification_id = specification_id;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugHasSpecificationKey that = (BugHasSpecificationKey) o;
        return bug_id == that.bug_id &&
                specification_id == that.specification_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bug_id, specification_id);
    }
}
