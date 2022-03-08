package com.example.popcornandsodaonline.models;

public class Book {
    private int id_book;
    private String name_book;
    private String cover_book;
    private String background_book;
    private double rating_book;
    private int year_book;
    private String description_book;

    public Book(){}

    public Book(int id_book, String name_book, String cover_book, String background_book, double rating_book, int year_book, String description_book) {
        this.id_book = id_book;
        this.name_book = name_book;
        this.cover_book = cover_book;
        this.background_book = background_book;
        this.rating_book = rating_book;
        this.year_book = year_book;
        this.description_book = description_book;
    }

    public String getBackground_book() {
        return background_book;
    }

    public void setBackground_book(String background_book) {
        this.background_book = background_book;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public String getCover_book() {
        return cover_book;
    }

    public void setCover_book(String cover_book) {
        this.cover_book = cover_book;
    }

    public double getRating_book() {
        return rating_book;
    }

    public void setRating_book(double rating_book) {
        this.rating_book = rating_book;
    }

    public int getYear_book() {
        return year_book;
    }

    public void setYear_book(int year_book) {
        this.year_book = year_book;
    }

    public String getDescription_book() {
        return description_book;
    }

    public void setDescription_book(String description_book) {
        this.description_book = description_book;
    }


}
