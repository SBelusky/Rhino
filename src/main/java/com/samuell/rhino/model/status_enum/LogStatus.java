package com.samuell.rhino.model.status_enum;

public enum LogStatus {
    //Bug status
    BUG_CREATE("Vytvorenie reportu"),
    BUG_EDIT("Editácia reportu"),
    BUG_DELETE("Vymazanie reportu"),

    //Attachment status
    ATTACHMENT_UPLOAD("Nahratie prílohy"),
    ATTACHMENT_DELETE("Vymazanie prílohy"),

    //Comment status
    COMMENT_CREATE("Vytvorenie komentára"),
    COMMENT_EDIT("Editácia komentára"),
    COMMENT_DELETE("Vymazanie komentára"),

    //Specification status
    STATUS_CHANGE("Zmena statusu"),
    PRIORITY_CHANGE("Zmena priority"),
    REPRODUCIBILITY_CHANGE("Zmena reprodukovateľnosti"),

    //Relation with other bug status
    RELATION_CREATE("Vytvorenie vzťahu"),
    RELATION_DELETE("Vymazanie vzťahu"),

    //Version status
    FOUND_IN_VERSION_CHANGE("Zmena verzie nájdenia"),
    REPAIRED_IN_VERSION_CHANGE("Zmena verzie vyriešenia"),

    //Category status
    CATEGORY_CHANGE("Zmena kategórie"),

    //Category status
    ASSOCIATED_USER_CHANGE("Zmena priradeného používateľa"),

    //Default if we dont choose from previous attributes
    DEFAULT("DEFAULT");

    public final String label;

    LogStatus(String label) {
        this.label = label;
    }
}
