package com.example.popcornandsodaonline.models;

public class Actor {
    private int id_actor;
    private String name_actor;
    private String nationality;
    private String description;
    private String cover_actor;

    public Actor(int id_actor, String name_actor, String nationality, String description, String cover_actor) {
        this.id_actor = id_actor;
        this.name_actor = name_actor;
        this.nationality = nationality;
        this.description = description;
        this.cover_actor = cover_actor;
    }

    public Actor(){}

    public int getId_actor() {
        return id_actor;
    }

    public void setId_actor(int id_actor) {
        this.id_actor = id_actor;
    }

    public String getName_actor() {
        return name_actor;
    }

    public void setName_actor(String name_actor) {
        this.name_actor = name_actor;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover_actor() {
        return cover_actor;
    }

    public void setCover_actor(String cover_actor) {
        this.cover_actor = cover_actor;
    }
}
