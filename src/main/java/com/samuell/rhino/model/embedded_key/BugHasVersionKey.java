package com.samuell.rhino.model.embedded_key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BugHasVersionKey implements Serializable {
    //Atributes
    @Column(name = "bug_id")
    private int bug_id;

    @Column(name = "version_id")
    private int version_id;

    //Constructors
    public BugHasVersionKey() {
    }

    public BugHasVersionKey(int bug_id, int version_id) {
        this.bug_id = bug_id;
        this.version_id = version_id;
    }

    //Getters & setters
    public int getBug_id() {
        return bug_id;
    }

    public void setBug_id(int bug_id) {
        this.bug_id = bug_id;
    }

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    //equals() and hashCode()
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugHasVersionKey that = (BugHasVersionKey) o;
        return bug_id == that.bug_id &&
                version_id == that.version_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bug_id, version_id);
    }
}
