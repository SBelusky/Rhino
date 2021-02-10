package com.samuell.rhino.model.embedded_key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BugHasBugKey implements Serializable {
    //Atributes
    @Column(name = "contains")
    private Integer contains;

    @Column(name = "included")
    private Integer included;

    //Constructors
    public BugHasBugKey() {
    }

    public BugHasBugKey(Integer contains, Integer included) {
        this.contains = contains;
        this.included = included;
    }

    //Getters & setters

    public Integer getContains() {
        return contains;
    }

    public void setContains(Integer contains) {
        this.contains = contains;
    }

    public int getIncluded() {
        return included;
    }

    public void setIncluded(Integer included) {
        this.included = included;
    }

    //equals() nad hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugHasBugKey that = (BugHasBugKey) o;
        return contains == that.contains &&
                included == that.included;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contains, included);
    }
}
