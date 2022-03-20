package com.example.popcornandsodaonline.models;

public class Author {
    private int id_author;
    private String name_author;
    private String nationality_author;
    private String description_author;
    private String cover_author;
    private String birthday;

    public Author() {
    }

    public Author(int id_author, String name_author, String nationality_author, String description_author, String cover_author) {
        this.id_author = id_author;
        this.name_author = name_author;
        this.nationality_author = nationality_author;
        this.description_author = description_author;
        this.cover_author = cover_author;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }

    public String getNationality_author() {
        return nationality_author;
    }

    public void setNationality_author(String nationality_author) {
        this.nationality_author = nationality_author;
    }

    public String getDescription_author() {
        return description_author;
    }

    public void setDescription_author(String description_author) {
        this.description_author = description_author;
    }

    public String getCover_author() {
        return cover_author;
    }

    public void setCover_author(String cover_author) {
        this.cover_author = cover_author;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
