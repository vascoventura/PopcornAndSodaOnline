package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Actors.ACTOR_BIRTHDAY;
import static com.example.popcornandsodaonline.database.DataParser_Actors.COVER_ACTOR;
import static com.example.popcornandsodaonline.database.DataParser_Actors.DESCRIPTION_ACTOR;
import static com.example.popcornandsodaonline.database.DataParser_Actors.ID_ACTOR;
import static com.example.popcornandsodaonline.database.DataParser_Actors.NAME_ACTOR;
import static com.example.popcornandsodaonline.database.DataParser_Actors.NATIONALITY_ACTOR;
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
import com.example.popcornandsodaonline.models.Actor;
import com.example.popcornandsodaonline.ui.Details_Actors;
import com.example.popcornandsodaonline.ui.Details_Movies;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Movie_Actors_Adapter extends RecyclerView.Adapter<Movie_Actors_Adapter.MyViewHolder>{

    Context context;
    List<Actor> list;


    public Movie_Actors_Adapter(Context context, List<Actor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Movie_Actors_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_list_movie_actors, viewGroup, false);
        return new Movie_Actors_Adapter.MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull Movie_Actors_Adapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.actorTitle.setText(list.get(i).getName_actor());
        try {
            URL imageurl = new URL(ConnectionDb.CONECTIONIP + list.get(i).getCover_actor());
            Glide.with(context).load(imageurl).into(myViewHolder.Imgactor);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView actorTitle;
        private ImageView Imgactor;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            actorTitle = itemView.findViewById(R.id.textView_movie_actors);
            Imgactor = itemView.findViewById(R.id.imageView_movie_actors);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idFilme = (int) list.get(getAdapterPosition()).getId_actor();
                    String capaFilme = list.get(getAdapterPosition()).getCover_actor();
                    String descricaoFilme = list.get(getAdapterPosition()).getDescription();
                    String nomeFilme = list.get(getAdapterPosition()).getName_actor();
                    String year = list.get(getAdapterPosition()).getBirthday();
                    String nationality = list.get(getAdapterPosition()).getNationality();

                    Context context = v.getContext();
                    Intent intent = new Intent();
                    intent.setClass(context, Details_Actors.class);
                    intent.putExtra(ID_ACTOR, idFilme);
                    intent.putExtra(COVER_ACTOR, capaFilme);
                    intent.putExtra(NAME_ACTOR,nomeFilme);
                    intent.putExtra(DESCRIPTION_ACTOR, descricaoFilme);
                    intent.putExtra(ACTOR_BIRTHDAY, String.valueOf(year));
                    intent.putExtra(NATIONALITY_ACTOR, nationality);

                    context.startActivity(intent);
                }
            });
        }
    }
}