package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.popcornandsodaonline.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button moviesButton = findViewById(R.id.menu_movies);
        Button showsButton = findViewById(R.id.menu_shows);
        Button booksButton = findViewById(R.id.menu_books);
        Button authorsButton = findViewById(R.id.menu_authors);
        Button actorsButton = findViewById(R.id.menu_actors);
        Button productionButton = findViewById(R.id.menu_productions);

        moviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Movies", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Movies.class);
                startActivity(intent);
            }
        });

        showsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Shows", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Shows.class);
                startActivity(intent);
            }
        });

        booksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Books", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Books.class);
                startActivity(intent);
            }
        });

        authorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Authors", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Authors.class);
                startActivity(intent);
            }
        });

        actorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Actors", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Actors.class);
                startActivity(intent);
            }
        });

        productionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Productors", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Productions.class);
                startActivity(intent);
            }
        });
    }
}