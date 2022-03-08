package com.example.popcornandsodaonline.models;

public class Show {
    private int id_show;
    private String name_show;
    private float rating;
    private String trailer_show;
    private String description_show;
    private int begin_year;
    private int end_year;
    private String cover_show;

    public Show() {}

    public Show(int id_show, String name_show, float rating, String trailer_show, String description_show, int begin_year, int end_year, String cover_show) {
        this.id_show = id_show;
        this.name_show = name_show;
        this.rating = rating;
        this.trailer_show = trailer_show;
        this.description_show = description_show;
        this.begin_year = begin_year;
        this.end_year = end_year;
        this.cover_show = cover_show;
    }

    public int getId_show() {
        return id_show;
    }

    public void setId_show(int id_show) {
        this.id_show = id_show;
    }

    public String getName_show() {
        return name_show;
    }

    public void setName_show(String name_show) {
        this.name_show = name_show;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTrailer_show() {
        return trailer_show;
    }

    public void setTrailer_show(String trailer_show) {
        this.trailer_show = trailer_show;
    }

    public String getDescription_show() {
        return description_show;
    }

    public void setDescription_show(String description_show) {
        this.description_show = description_show;
    }

    public int getBegin_year() {
        return begin_year;
    }

    public void setBegin_year(int begin_year) {
        this.begin_year = begin_year;
    }

    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    public String getCover_show() {
        return cover_show;
    }

    public void setCover_show(String cover_show) {
        this.cover_show = cover_show;
    }
}
