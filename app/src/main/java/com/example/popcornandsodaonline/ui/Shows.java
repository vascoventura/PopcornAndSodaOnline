package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.database.Downloader_Actors;
import com.example.popcornandsodaonline.database.Downloader_Shows;
import com.example.popcornandsodaonline.models.Actor;
import com.example.popcornandsodaonline.models.Show;

import java.util.ArrayList;

public class Shows extends AppCompatActivity {

    private ArrayList<Show> showsList = new ArrayList<>();

    String url = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_DIRECTORY + ConnectionDb.PHP_SHOWS_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);
        GridView gridViewShows = (GridView) findViewById(R.id.shows_grid);
        new Downloader_Shows(Shows.this, this, url, gridViewShows).execute();
    }
}