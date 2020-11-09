package com.samuell.rhino.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Lob;
import java.sql.Timestamp;

public class AttachmentDto {
    //Attributes
    private Integer id;
    @Lob
    private byte[] file;
    private String type;
    private String size;
    private String name;
    private UserDto user;
    private BugDto bug;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Timestamp created_at;
    private Integer idOfLastEditingUser;

    //Constructors
    public AttachmentDto() {
    }

    public AttachmentDto(Integer id, String name, UserDto user, BugDto bug, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.bug = bug;
        this.created_at = created_at;
    }

    //Getters & setters

    public Integer getIdOfLastEditingUser() {
        return idOfLastEditingUser;
    }

    public void setIdOfLastEditingUser(Integer idOfLastEditingUser) {
        this.idOfLastEditingUser = idOfLastEditingUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public BugDto getBug() {
        return bug;
    }

    public void setBug(BugDto bug) {
        this.bug = bug;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
