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
import com.example.popcornandsodaonline.database.DataParser_Movies;
import com.example.popcornandsodaonline.database.DataParser_Shows;
import com.example.popcornandsodaonline.database.Downloader_Actors_Movies;
import com.example.popcornandsodaonline.database.Downloader_Categories;
import com.example.popcornandsodaonline.database.Downloader_Detail_Movies;
import com.example.popcornandsodaonline.database.Downloader_Detail_Shows;
import com.example.popcornandsodaonline.database.Downloader_Productors_Movies;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.models.Show;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Details_Shows extends AppCompatActivity {

    private Show show;

    private ArrayList<Show> showList = new ArrayList<>();

    ViewPager sliderpager;

    //URL´s
    String url_backgrounds = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_BACKGROUND_SHOWS_FILE;
    String url_categories = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_CATEGORY_NAME_SHOW_FILE;
    String url_productors = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_SHOW_PRODUCTOR_FILE;
    String url_actors = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_SHOW_ACTORS_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_shows);

        TextView textViewNome = findViewById(R.id.detail_nome_serie);
        TextView textViewTipo = findViewById(R.id.detail_show_cathegory);
        TextView textViewClassificacao = findViewById(R.id.detail_show_classificacao);
        TextView textViewAno = findViewById(R.id.detail_show_ano);
        TextView textViewDescricao = findViewById(R.id.detail_show_descricao);
        ImageView imageViewCapa = findViewById(R.id.imageViewCapaShow);
        sliderpager = findViewById(R.id.sliderPage_Shows);
        TextView textViewAnoFim = findViewById(R.id.detail_show_ano_end);

        FloatingActionButton favorito = findViewById(R.id.botao_favorito_show);
        FloatingActionButton visto = findViewById(R.id.botao_visto_shows);
        FloatingActionButton trailer = findViewById(R.id.detail_show_trailer);

        RecyclerView productionRV = findViewById(R.id.production_show_rv);
        RecyclerView actorsRv = findViewById(R.id.actor_show_rv);

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
        actorsRv.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewAnoFim.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));

        Intent intent = getIntent();
        final int idShow = (int) intent.getIntExtra(DataParser_Shows.ID_SHOW, -1);
        if (idShow == -1) {
            Toast.makeText(this, "Erro: não foi possível abrir a página do conteúdo", Toast.LENGTH_LONG).show();
            finish();
            return;
        } else{
            final String nameShow = intent.getStringExtra(DataParser_Shows.NAME_SHOW);
            final String ratingShow = intent.getStringExtra(DataParser_Shows.RATING_SHOW);
            final String yearShow = intent.getStringExtra(DataParser_Shows.YEAR_SHOW);
            final String yearEndShow = intent.getStringExtra(DataParser_Shows.YEAR_END_SHOW);
            final String trailerMovie = intent.getStringExtra(DataParser_Shows.TRAILER_SHOW);
            final String coverShow = intent.getStringExtra(DataParser_Shows.COVER_SHOW);
            final String descriptionShow = intent.getStringExtra(DataParser_Shows.DESCRIPTION_SHOW);

            textViewNome.setText(nameShow);
            textViewClassificacao.setText(ratingShow);
            textViewAno.setText(yearShow);
            textViewDescricao.setText(descriptionShow);
            textViewAnoFim.setText(yearEndShow);

            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + coverShow);
                Glide.with(this.getApplicationContext()).load(imageurl).into(imageViewCapa);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //Carregar as imagens de fundo
            new Downloader_Detail_Shows(Details_Shows.this,this, url_backgrounds + idShow, sliderpager).execute();

            //Carregar as categorias
            new Downloader_Categories(Details_Shows.this,this, url_categories + idShow, textViewTipo).execute();

            //Carregar os produtores
            productionRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Productors_Movies(Details_Shows.this, this, url_productors + idShow, productionRV).execute();

            //Carrergar os actores
            actorsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Actors_Movies(Details_Shows.this, this, url_actors + idShow, actorsRv).execute();
        }
    }
}