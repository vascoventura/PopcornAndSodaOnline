package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.database.Downloader_Books;
import com.example.popcornandsodaonline.database.Downloader_Movies;
import com.example.popcornandsodaonline.models.Movie;

import java.util.ArrayList;

public class Books extends AppCompatActivity {
    private ArrayList<Movie> movieList = new ArrayList<>();

    String url = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_DIRECTORY + ConnectionDb.PHP_BOOKS_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        GridView gridViewBooks = (GridView) findViewById(R.id.books_grid);
        new Downloader_Books(Books.this, this, url, gridViewBooks).execute();
    }
}