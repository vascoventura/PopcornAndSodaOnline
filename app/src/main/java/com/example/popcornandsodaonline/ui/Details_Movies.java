package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.database.DataParser_Movies;
import com.example.popcornandsodaonline.database.Downloader_Actors_Movies;
import com.example.popcornandsodaonline.database.Downloader_Categories;
import com.example.popcornandsodaonline.database.Downloader_Detail_Movies;
import com.example.popcornandsodaonline.database.Downloader_Productors_Movies;
import com.example.popcornandsodaonline.models.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Details_Movies extends AppCompatActivity {

    private Movie movie;

    private ArrayList<Movie> movieList = new ArrayList<>();

    ViewPager sliderpager;

    //URL´s
    String url_backgrounds = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_BACKGROUND_MOVIES_FILE;
    String url_categories = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_CATEGORY_NAME_MOVIE_FILE;
    String url_productors = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_PRODUCTOR_MOVIE_FILE;
    String url_actors = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_ACTORS_MOVIE_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movies);

        TextView textViewNome = findViewById(R.id.detail_autor_nome);
        TextView textViewTipo = findViewById(R.id.detail_movie_cathegory);
        TextView textViewClassificacao = findViewById(R.id.detail_movie_classificacao);
        TextView textViewAno = findViewById(R.id.detail_movie_ano);
        TextView textViewDescricao = findViewById(R.id.detail_movie_descricao);
        ImageView imageViewCapa = findViewById(R.id.imageViewCapaFilme);
        sliderpager = findViewById(R.id.sliderPage_Movies);
        FloatingActionButton favorito = findViewById(R.id.botao_favorito);
        FloatingActionButton visto = findViewById(R.id.botao_visto);
        FloatingActionButton trailer = findViewById(R.id.detail_movie_trailer);

        RecyclerView productionRV = findViewById(R.id.production_movie_rv);
        RecyclerView actorsRv = findViewById(R.id.actor_movie_rv);

        textViewNome.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewTipo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewClassificacao.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewAno.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewDescricao.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        favorito.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        visto.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        trailer.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        imageViewCapa.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        sliderpager.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        productionRV.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));


        Intent intent = getIntent();
        final long idFilme = intent.getLongExtra(DataParser_Movies.ID_MOVIE, -1);
        if (idFilme == -1) {
            Toast.makeText(this, "Erro: não foi possível abrir a página do conteúdo", Toast.LENGTH_LONG).show();
            finish();
            return;
        } else{
            final String nameMovie = intent.getStringExtra(DataParser_Movies.NAME_MOVIE);
            final String ratingMovie = intent.getStringExtra(DataParser_Movies.RATING_MOVIE);
            final String yearMovie = intent.getStringExtra(DataParser_Movies.YEAR_MOVIE);
            final String trailerMovie = intent.getStringExtra(DataParser_Movies.TRAILER_MOVIE);
            final  String coverMovie = intent.getStringExtra(DataParser_Movies.COVER_MOVIE);
            final  String descriptionMovie = intent.getStringExtra(DataParser_Movies.DESCRIPTION_MOVIE);

            textViewNome.setText(nameMovie);
            textViewClassificacao.setText(ratingMovie);
            textViewAno.setText(yearMovie);
            textViewDescricao.setText(descriptionMovie);

            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + coverMovie);
                Glide.with(this.getApplicationContext()).load(imageurl).into(imageViewCapa);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //Carregar as imagens de fundo
            new Downloader_Detail_Movies(Details_Movies.this,this, url_backgrounds + idFilme, sliderpager).execute();

            //Carregar as categorias
            new Downloader_Categories(Details_Movies.this,this, url_categories + idFilme, textViewTipo).execute();

            //Carregar os produtores
            productionRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Productors_Movies(Details_Movies.this, this, url_productors + idFilme, productionRV).execute();

            //Carrergar os actores
            actorsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Actors_Movies(Details_Movies.this, this, url_actors + idFilme, actorsRv).execute();
        }
    }
}