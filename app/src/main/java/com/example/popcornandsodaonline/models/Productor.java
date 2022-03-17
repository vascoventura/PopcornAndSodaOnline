package com.example.popcornandsodaonline.models;

public class Productor {
    private int id_productor;
    private String name_productor;
    private String nationality;
    private String description;
    private String cover_productor;

    public Productor() {}

    public Productor(int id_productor, String name_productor, String nationality, String description, String cover_productor) {
        this.id_productor = id_productor;
        this.name_productor = name_productor;
        this.nationality = nationality;
        this.description = description;
        this.cover_productor = cover_productor;
    }

    public Productor(String name_productor, String cover_productor) {
        this.name_productor = name_productor;
        this.cover_productor = cover_productor;
    }

    public int getId_productor() {
        return id_productor;
    }

    public void setId_productor(int id_productor) {
        this.id_productor = id_productor;
    }

    public String getName_productor() {
        return name_productor;
    }

    public void setName_productor(String name_productor) {
        this.name_productor = name_productor;
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

    public String getCover_productor() {
        return cover_productor;
    }

    public void setCover_productor(String cover_productor) {
        this.cover_productor = cover_productor;
    }
}
