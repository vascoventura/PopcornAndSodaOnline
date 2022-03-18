package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Movies.ID_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Productors.ID_PRODUCTION;

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
import com.example.popcornandsodaonline.models.Productor;
import com.example.popcornandsodaonline.ui.Details_Movies;
import com.example.popcornandsodaonline.ui.Details_Productions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Productor_Movies_Adapter extends RecyclerView.Adapter<Productor_Movies_Adapter.MyViewHolder> implements View.OnClickListener {

    Context context;
    List<Movie> list;

    public Productor_Movies_Adapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Productor_Movies_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_list_movie_production, viewGroup, false);
        return new Productor_Movies_Adapter.MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull Productor_Movies_Adapter.MyViewHolder myViewHolder, int i) {
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

    @Override
    public void onClick(View view) {
        System.out.println("FILME CLICK: " + list.get(view.getId()).getId_movie());
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
                    System.out.print("id do produtor: " + idFilme);
                    Context context = v.getContext();
                    Intent intent = new Intent();
                    intent.setClass(context, Details_Movies.class);
                    intent.putExtra(ID_MOVIE, idFilme);
                    context.startActivity(intent);
                }
            });
        }
    }
}