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
import com.example.popcornandsodaonline.database.DataParser_Books;
import com.example.popcornandsodaonline.database.DataParser_Movies;
import com.example.popcornandsodaonline.database.Downloader_Authors_Books;
import com.example.popcornandsodaonline.database.Downloader_Categories;
import com.example.popcornandsodaonline.database.Downloader_Productors_Movies;
import com.example.popcornandsodaonline.models.Book;
import com.example.popcornandsodaonline.models.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Details_Books extends AppCompatActivity {

    String url_authors = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_BOOK_AUTHORS_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_books);

        TextView textViewNome = findViewById(R.id.detail_nome_book);
        //TextView textViewTipo = findViewById(R.id.detail_movie_cathegory);
        TextView textViewClassificacao = findViewById(R.id.detail_book_classificacao);
        TextView textViewAno = findViewById(R.id.detail_book_year);
        TextView textViewDescricao = findViewById(R.id.detail_book_descricao);
        ImageView imageViewCapa = findViewById(R.id.imageViewCapaBook);
        FloatingActionButton favorito = findViewById(R.id.botao_favorito_book);
        FloatingActionButton lido = findViewById(R.id.botao_lido_book);
        ImageView imageViewFundo = findViewById(R.id.sliderPage_Books);

        RecyclerView authorsRV = findViewById(R.id.book_authors_rv);

        textViewNome.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        //textViewTipo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewClassificacao.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewAno.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewDescricao.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        favorito.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        lido.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        imageViewCapa.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        imageViewFundo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        authorsRV.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));

        Intent intent = getIntent();
        final int idBook = (int) intent.getIntExtra(DataParser_Books.ID_BOOK, -1);
        if (idBook == -1) {
            Toast.makeText(this, "Erro: não foi possível abrir a página do conteúdo", Toast.LENGTH_LONG).show();
            finish();
            return;
        } else {
            final String nameBook = intent.getStringExtra(DataParser_Books.NAME_BOOK);
            final String ratingBook = intent.getStringExtra(DataParser_Books.RATING_BOOK);
            final String yearBook = intent.getStringExtra(DataParser_Books.YEAR_BOOK);
            final String coverBook = intent.getStringExtra(DataParser_Books.COVER_BOOK);
            final String backgroundBook = intent.getStringExtra(DataParser_Books.BACKGROUND_BOOK);
            final String descriptionBook = intent.getStringExtra(DataParser_Books.DESCRIPTION_BOOK);

            textViewNome.setText(nameBook);
            textViewClassificacao.setText(ratingBook);
            textViewAno.setText(yearBook);
            textViewDescricao.setText(descriptionBook);

            try {
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + coverBook);
                Glide.with(this.getApplicationContext()).load(imageurl).into(imageViewCapa);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + backgroundBook);
                Glide.with(this.getApplicationContext()).load(imageurl).into(imageViewFundo);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //Carregar as categorias
            //new Downloader_Categories(Details_Movies.this,this, url_categories + idFilme, textViewTipo).execute();

            //Carregar os produtores
            authorsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Authors_Books(Details_Books.this, this, url_authors + idBook, authorsRV).execute();
        }
    }
}