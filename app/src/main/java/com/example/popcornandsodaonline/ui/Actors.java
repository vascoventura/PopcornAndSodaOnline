package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.database.Downloader_Actors;
import com.example.popcornandsodaonline.database.Downloader_Books;
import com.example.popcornandsodaonline.models.Actor;
import com.example.popcornandsodaonline.models.Movie;

import java.util.ArrayList;

public class Actors extends AppCompatActivity {
    private ArrayList<Actor> actorList = new ArrayList<>();

    String url = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_DIRECTORY + ConnectionDb.PHP_ACTORS_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors);
        GridView gridViewActors = (GridView) findViewById(R.id.actors_grid);
        new Downloader_Actors(Actors.this, this, url, gridViewActors).execute();
    }
}