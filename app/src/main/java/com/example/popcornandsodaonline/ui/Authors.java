package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.database.Downloader_Authors;
import com.example.popcornandsodaonline.database.Downloader_Books;
import com.example.popcornandsodaonline.models.Actor;
import com.example.popcornandsodaonline.models.Author;

import java.util.ArrayList;

public class Authors extends AppCompatActivity {
    private ArrayList<Author> authorList = new ArrayList<>();

    String url = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_DIRECTORY + ConnectionDb.PHP_AUTHORS_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        GridView gridViewAuthors = (GridView) findViewById(R.id.authors_grid);
        new Downloader_Authors(Authors.this, this, url, gridViewAuthors).execute();
    }
}