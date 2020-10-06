package com.samuell.rhino.model.embedded_key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BugHasBugKey implements Serializable {
    //Atributes
    @Column(name = "bug_id1")
    int contains;

    @Column(name = "bug_id2")
    int included;

    //Constructors
    public BugHasBugKey() {
    }

    public BugHasBugKey(int contains, int included) {
        this.contains = contains;
        this.included = included;
    }

    //Getters & setters
    public int getContains() {
        return contains;
    }

    public void setContains(int contains) {
        this.contains = contains;
    }

    public int getIncluded() {
        return included;
    }

    public void setIncluded(int included) {
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
