package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.database.Downloader_Productions;
import com.example.popcornandsodaonline.models.Productor;

import java.util.ArrayList;

public class Productions extends AppCompatActivity {

    private ArrayList<Productor> productorsList = new ArrayList<>();

    String url = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_DIRECTORY + ConnectionDb.PHP_PRODUCTORS_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productions);
        GridView gridViewProductors = (GridView) findViewById(R.id.productions_grid);
        new Downloader_Productions(Productions.this, this, url, gridViewProductors).execute();
    }
}