package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Movies.COVER_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Movies.DESCRIPTION_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Movies.ID_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Movies.NAME_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Movies.RATING_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Movies.TRAILER_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Movies.YEAR_MOVIE;

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
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.ui.Details_Movies;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Actor_Movies_Adapter extends RecyclerView.Adapter<Actor_Movies_Adapter.MyViewHolder> {
    Context context;
    List<Movie> list;

    public Actor_Movies_Adapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Actor_Movies_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_list_movie_production, viewGroup, false);
        return new Actor_Movies_Adapter.MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull Actor_Movies_Adapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.movieTitle.setText(list.get(i).getName_movie());
        try {
            URL imageurl = new URL(ConnectionDb.CONECTIONIP + list.get(i).getCover_image_movie());
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
                    int idFilme = (int) list.get(getAdapterPosition()).getId_movie();
                    String capaFilme = list.get(getAdapterPosition()).getCover_image_movie();
                    String descricaoFilme = list.get(getAdapterPosition()).getDescription_movie();
                    String nomeFilme = list.get(getAdapterPosition()).getName_movie();
                    float rating = (float) list.get(getAdapterPosition()).getRating_movie();
                    int year = list.get(getAdapterPosition()).getYear_movie();
                    String trailer = list.get(getAdapterPosition()).getLink_trailer_filme();

                    Context context = v.getContext();
                    Intent intent = new Intent();
                    intent.setClass(context, Details_Movies.class);
                    intent.putExtra(ID_MOVIE, idFilme);
                    intent.putExtra(COVER_MOVIE, capaFilme);
                    intent.putExtra(NAME_MOVIE,nomeFilme);
                    intent.putExtra(DESCRIPTION_MOVIE, descricaoFilme);
                    intent.putExtra(RATING_MOVIE, String.valueOf(rating));
                    intent.putExtra(YEAR_MOVIE, String.valueOf(year));
                    intent.putExtra(TRAILER_MOVIE, trailer);

                    context.startActivity(intent);
                }
            });
        }
    }
}
