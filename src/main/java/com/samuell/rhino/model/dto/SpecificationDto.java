package com.samuell.rhino.model.dto;

import java.sql.Timestamp;

public class SpecificationDto {
    //Attributes
    private Integer id;
    private String name;
    private String description;
    private String type;
    private String color;
    private Timestamp created_at;

    //Constructors
    public SpecificationDto() {
    }

    public SpecificationDto(Integer id, String name, String description, String type, String color, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.color = color;
        this.created_at = created_at;
    }

    //Getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
