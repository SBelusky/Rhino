package com.samuell.rhino.model.status_enum;

public enum LogStatus {
    //Bug status
    BUG_CREATE("Bug created"),
    BUG_EDIT("Bug edited"),
    BUG_DELETE("Bug deleted"),
    //Attachment status
    ATTACHMENT_UPLOAD("Attachment uploaded"),
    ATTACHMENT_DELETE("Attachment deleted"),
    //Comment status
    COMMENT_CREATE("Comment created"),
    COMMENT_EDIT("Comment edited"),
    COMMENT_DELETE("Comment deleted"),
    //Specification status
    TYPE_CHANGE("Type changed"),
    PRIORITY_CHANGE("Priority changed"),
    REPRODUCIBILITY_CHANGE("Reproducibility changed"),
    //Relation with other bug status
    RELATION_CREATE("Relation created"),
    RELATION_DELETE("Relation deleted"),
    //Version status
    FOUND_IN_VERSION_CHANGE("Found in version changed"),
    REPAIRED_IN_VERSION_CHANGE("Repaired in version changed"),
    //Category status
    CATEGORY_CHANGE("Category changed"),
    //Default if we dont choose from previous attributes
    DEFAULT("DEFAULT");

    public final String label;

    LogStatus(String label) {
        this.label = label;
    }
}
