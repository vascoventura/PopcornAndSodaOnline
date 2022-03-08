package com.example.popcornandsodaonline.models;

public class Movie {
    //Atributos
    private long id_movie;
    private String name_movie;
    private double rating_movie;
    private int year_movie;
    private String description_movie;
    private String cover_image_movie;
    private String link_trailer_filme;


    private byte[] foto_fundo_filme;
    private long foto_fundo;

    private boolean favorito_filme;
    private boolean visto_filme;

    private long categoria_filme;
    private String nomeCategoria;

    public Movie(){}

    public Movie(long id_movie, String name_movie, double rating_movie, int year_movie, String description_movie, String cover_image_movie, String link_trailer_filme) {
        this.id_movie = id_movie;
        this.name_movie = name_movie;
        this.rating_movie = rating_movie;
        this.year_movie = year_movie;
        this.description_movie = description_movie;
        this.cover_image_movie = cover_image_movie;
        this.link_trailer_filme = link_trailer_filme;
    }

    public long getId_movie() {
        return id_movie;
    }

    public void setId_movie(long id_movie) {
        this.id_movie = id_movie;
    }

    public String getName_movie() {
        return name_movie;
    }

    public void setName_movie(String name_movie) {
        this.name_movie = name_movie;
    }

    public double getRating_movie() {
        return rating_movie;
    }

    public void setRating_movie(double rating_movie) {
        this.rating_movie = rating_movie;
    }

    public int getYear_movie() {
        return year_movie;
    }

    public void setYear_movie(int year_movie) {
        this.year_movie = year_movie;
    }

    public String getDescription_movie() {
        return description_movie;
    }

    public void setDescription_movie(String description_movie) {
        this.description_movie = description_movie;
    }

    public String getCover_image_movie() {
        return cover_image_movie;
    }

    public void setCover_image_movie(String cover_image_movie) {
        this.cover_image_movie = cover_image_movie;
    }

    public String getLink_trailer_filme() {
        return link_trailer_filme;
    }

    public void setLink_trailer_filme(String link_trailer_filme) {
        this.link_trailer_filme = link_trailer_filme;
    }


}
