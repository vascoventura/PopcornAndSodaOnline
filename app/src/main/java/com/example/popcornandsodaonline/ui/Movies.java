package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.adapters.MovieGridAdapter;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.database.Downloader_Movies;
import com.example.popcornandsodaonline.models.Movie;

import java.util.ArrayList;

public class Movies extends AppCompatActivity {
    private ArrayList<Movie> movieList = new ArrayList<>();

    String url = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_DIRECTORY + ConnectionDb.PHP_MOVIES_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        GridView gridViewMovies = (GridView) findViewById(R.id.movies_grid);
        new Downloader_Movies(Movies.this, this, url, gridViewMovies).execute();
    }
}