package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Books.BACKGROUND_BOOK;
import static com.example.popcornandsodaonline.database.DataParser_Books.COVER_BOOK;
import static com.example.popcornandsodaonline.database.DataParser_Books.DESCRIPTION_BOOK;
import static com.example.popcornandsodaonline.database.DataParser_Books.ID_BOOK;
import static com.example.popcornandsodaonline.database.DataParser_Books.NAME_BOOK;
import static com.example.popcornandsodaonline.database.DataParser_Books.RATING_BOOK;
import static com.example.popcornandsodaonline.database.DataParser_Books.YEAR_BOOK;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.models.Book;
import com.example.popcornandsodaonline.ui.Details_Books;
import com.example.popcornandsodaonline.ui.Details_Movies;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Book_Authors_Adapter extends RecyclerView.Adapter<Book_Authors_Adapter.MyViewHolder> {
    Context context;
    List<Book> list;

    public Book_Authors_Adapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Book_Authors_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_list_movie_production, viewGroup, false);
        return new Book_Authors_Adapter.MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull Book_Authors_Adapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.movieTitle.setText(list.get(i).getName_book());
        try {
            URL imageurl = new URL(ConnectionDb.CONECTIONIP + list.get(i).getCover_book());
            Glide.with(context).load(imageurl).into(myViewHolder.ImgMovie);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView movieTitle;
        private ImageView ImgMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.textView_movie_production);
            ImgMovie = itemView.findViewById(R.id.imageView_movie_production);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idBook = (int) list.get(getAdapterPosition()).getId_book();
                    String capaFilme = list.get(getAdapterPosition()).getCover_book();
                    String descricaoFilme = list.get(getAdapterPosition()).getDescription_book();
                    String nomeFilme = list.get(getAdapterPosition()).getName_book();
                    float rating = (float) list.get(getAdapterPosition()).getRating_book();
                    String year = list.get(getAdapterPosition()).getYear();
                    String backbook = list.get(getAdapterPosition()).getBackground_book();

                    Context context = v.getContext();
                    Intent intent = new Intent();
                    intent.setClass(context, Details_Books.class);
                    intent.putExtra(ID_BOOK, idBook);
                    intent.putExtra(COVER_BOOK, capaFilme);
                    intent.putExtra(NAME_BOOK,nomeFilme);
                    intent.putExtra(DESCRIPTION_BOOK, descricaoFilme);
                    intent.putExtra(RATING_BOOK, String.valueOf(rating));
                    intent.putExtra(YEAR_BOOK, String.valueOf(year));
                    intent.putExtra(BACKGROUND_BOOK, backbook);

                    context.startActivity(intent);
                }
            });
        }
    }
}
