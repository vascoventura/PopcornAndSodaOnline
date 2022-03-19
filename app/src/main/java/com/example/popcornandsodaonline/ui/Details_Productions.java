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
import com.example.popcornandsodaonline.database.DataParser_Productors;
import com.example.popcornandsodaonline.database.Downloader_Detail_Productions;
import com.example.popcornandsodaonline.database.Downloader_Movies_Productors;
import com.example.popcornandsodaonline.database.Downloader_Shows_Productors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;

public class Details_Productions extends AppCompatActivity {

    ViewPager sliderpager;

    //URL´s
    String url_backgrounds = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_BACKGROUND_PRODUCTIONS_FILE;
    String url_movies = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_PRODUCTOR_MOVIE_FILE;
    String url_shows = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_PRODUCTOR_SHOW_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_productions);

        TextView textViewNome = findViewById(R.id.detail_production_nome);
        TextView textViewAno = findViewById(R.id.detail_production_birthday);
        TextView textViewDescricao = findViewById(R.id.detail_production_descricao);
        ImageView imageViewCapa = findViewById(R.id.imageViewCapaProduction);
        sliderpager = findViewById(R.id.sliderPage_Production);
        FloatingActionButton favorito = findViewById(R.id.botao_favorito_production);
        TextView textViewNationality = findViewById(R.id.detail_production_nationality);
        //TextView textViewDeath = findViewById(R.id.detail_production_death);

        RecyclerView moviesRV = findViewById(R.id.movie_production_rv);
        RecyclerView showsRv = findViewById(R.id.show_production_rv);

        textViewNome.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewAno.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewDescricao.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        favorito.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        imageViewCapa.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        sliderpager.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        moviesRV.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        showsRv.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewNationality.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        //textViewDeath.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));


        Intent intent = getIntent();
        final int idProduction = (int) intent.getIntExtra(DataParser_Productors.ID_PRODUCTION, -1);
        if (idProduction == -1) {
            Toast.makeText(this, "Erro: não foi possível abrir a página do conteúdo", Toast.LENGTH_LONG).show();
            finish();
            return;
        } else{
            final String nameProduction = intent.getStringExtra(DataParser_Productors.NAME_PRODUCTION);
            final String yearProduction = intent.getStringExtra(DataParser_Productors.YEAR_BIRTHDAY);
            final String coverProduction = intent.getStringExtra(DataParser_Productors.COVER_PRODUCTION);
            final String descriptionProduction = intent.getStringExtra(DataParser_Productors.DESCRIPTION_PRODUCTION);
            final String nationalityProduction = intent.getStringExtra(DataParser_Productors.NATIONALITY_PRODUCTION);

            textViewNome.setText(nameProduction);
            textViewAno.setText(yearProduction);
            textViewDescricao.setText(descriptionProduction);
            textViewNationality.setText(nationalityProduction);

            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + coverProduction);
                Glide.with(this.getApplicationContext()).load(imageurl).into(imageViewCapa);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //Carregar as imagens de fundo
            new Downloader_Detail_Productions(Details_Productions.this,this, url_backgrounds + idProduction, sliderpager).execute();


            //Carregar os filmes
            moviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Movies_Productors(Details_Productions.this, this, url_movies + idProduction, moviesRV).execute();


            //Carregar as series
            showsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Shows_Productors(Details_Productions.this, this, url_shows + idProduction, showsRv).execute();
        }
    }
}