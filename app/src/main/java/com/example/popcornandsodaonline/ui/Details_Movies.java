package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.models.Movie;

public class Details_Movies extends AppCompatActivity {

    private Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movies);

        


    }
}