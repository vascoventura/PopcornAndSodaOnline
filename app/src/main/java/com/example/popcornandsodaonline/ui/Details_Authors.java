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
import com.example.popcornandsodaonline.database.DataParser_Authors;
import com.example.popcornandsodaonline.database.Downloader_Books_Authors;
import com.example.popcornandsodaonline.database.Downloader_Detail_Actors;
import com.example.popcornandsodaonline.database.Downloader_Detail_Authors;
import com.example.popcornandsodaonline.database.Downloader_Movies_Actors;
import com.example.popcornandsodaonline.database.Downloader_Shows_Actor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;

public class Details_Authors extends AppCompatActivity {

    String url_backgrounds = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_BACKGROUND_AUTHORS_FILE;
    String url_books = ConnectionDb.CONECTIONIP + ConnectionDb.PHP_AUTHOR_BOOKS_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_authors);

        TextView textViewNome = findViewById(R.id.detail_author_nome);
        TextView textViewAno = findViewById(R.id.detail_author_birthday);
        TextView textViewDescricao = findViewById(R.id.detail_author_descricao);
        ImageView imageViewCapa = findViewById(R.id.imageViewCapaAuthor);

        ViewPager sliderpager = findViewById(R.id.sliderPage_Authors);
        FloatingActionButton favorito = findViewById(R.id.botao_favorito_author);
        TextView textViewNationality = findViewById(R.id.detail_author_nationality);

        RecyclerView booksRV = findViewById(R.id.author_books_rv);

        textViewNome.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewAno.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewDescricao.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        favorito.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        imageViewCapa.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        sliderpager.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        booksRV.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));
        textViewNationality.setAnimation(AnimationUtils.loadAnimation(this, R.anim.nav_default_pop_enter_anim));

        Intent intent = getIntent();
        final int idAuthor = (int) intent.getIntExtra(DataParser_Authors.ID_AUTHOR, -1);
        if (idAuthor == -1) {
            Toast.makeText(this, "Erro: não foi possível abrir a página do conteúdo", Toast.LENGTH_LONG).show();
            finish();
            return;
        } else{
            final String nameAuthor = intent.getStringExtra(DataParser_Authors.NAME_AUTHOR);
            final String yearAuthor = intent.getStringExtra(DataParser_Authors.BIRTHDAY_AUTHOR);
            final String coverAuthor = intent.getStringExtra(DataParser_Authors.COVER_AUTHOR);
            final String descriptionAuthor = intent.getStringExtra(DataParser_Authors.DESCRIPTION_AUTHOR);
            final String nationalityAuthor = intent.getStringExtra(DataParser_Authors.NATIONALITY);

            textViewNome.setText(nameAuthor);
            textViewAno.setText(yearAuthor);
            textViewDescricao.setText(descriptionAuthor);
            textViewNationality.setText(nationalityAuthor);

            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + coverAuthor);
                Glide.with(this.getApplicationContext()).load(imageurl).into(imageViewCapa);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //Carregar as imagens de fundo
            new Downloader_Detail_Authors(Details_Authors.this,this, url_backgrounds + idAuthor, sliderpager).execute();


            //Carregar os livros
            booksRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            new Downloader_Books_Authors(Details_Authors.this, this, url_books + idAuthor, booksRV).execute();

        }
    }
}