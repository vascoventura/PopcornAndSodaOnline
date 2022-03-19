package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.database.DataParser_Actors;
import com.example.popcornandsodaonline.database.Downloader_Detail_Actors;
import com.example.popcornandsodaonline.database.Downloader_Movies_Actors;
import com.example.popcornandsodaonline.database.Downloader_Shows_Actor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;

public class Details_Actors extends AppCompatActivity {

    ViewPager sliderpager;

    //URL´s
    String url_backgrounds = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_BACKGROUND_ACTORS_FILE;
    String url_movies = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_ACTORS_MOVIE_FILE;
    String url_shows = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_ACTORS_SHOW_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_actors);

        TextView textViewNome = findViewById(R.id.detail_actor_nome);
        TextView textViewAno = findViewById(R.id.detail_actor_birthday);
        TextView textViewDescricao = findViewById(R.id.detail_actor_descricao);
        ImageView imageViewCapa = findViewById(R.id.imageViewCapaActor);
        sliderpager = findViewById(R.id.sliderPage_Actors);
        FloatingActionButton favorito = findViewById(R.id.botao_favorito_actor);
        TextView textViewNationality = findViewById(R.id.detail_actor_nationality);

        RecyclerView moviesRV = findViewById(R.id.movie_actor_rv);
        RecyclerView showsRv = findViewById(R.id.show_actor_rv);

        textViewNome.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewAno.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewDescricao.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        favorito.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        imageViewCapa.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        sliderpager.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        moviesRV.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        showsRv.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewNationality.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));

        Intent intent = getIntent();
        final int idActor = (int) intent.getIntExtra(DataParser_Actors.ID_ACTOR, -1);
        if (idActor == -1) {
            Toast.makeText(this, "Erro: não foi possível abrir a página do conteúdo", Toast.LENGTH_LONG).show();
            finish();
            return;
        } else{
            final String nameActor = intent.getStringExtra(DataParser_Actors.NAME_ACTOR);
            final String yearActor = intent.getStringExtra(DataParser_Actors.ACTOR_BIRTHDAY);
            final String coverActor = intent.getStringExtra(DataParser_Actors.COVER_ACTOR);
            final String descriptionActor = intent.getStringExtra(DataParser_Actors.DESCRIPTION_ACTOR);
            final String nationalityActor = intent.getStringExtra(DataParser_Actors.NATIONALITY_ACTOR);

            textViewNome.setText(nameActor);
            textViewAno.setText(yearActor);
            textViewDescricao.setText(descriptionActor);
            textViewNationality.setText(nationalityActor);

            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + coverActor);
                Glide.with(this.getApplicationContext()).load(imageurl).into(imageViewCapa);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //Carregar as imagens de fundo
            new Downloader_Detail_Actors(Details_Actors.this,this, url_backgrounds + idActor, sliderpager).execute();


            //Carregar os filmes
            moviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Movies_Actors(Details_Actors.this, this, url_movies + idActor, moviesRV).execute();


            //Carregar as series
            showsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Shows_Actor(Details_Actors.this, this, url_shows + idActor, showsRv).execute();
        }
    }
}