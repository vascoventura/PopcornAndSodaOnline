package com.example.popcornandsodaonline.adapters;

import android.content.Context;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Movie_Actors_Adapter extends RecyclerView.Adapter<Movie_Actors_Adapter.MyViewHolder>{

    Context context;
    List<Actor> list;

    /*public Movie_Actors_Adapter(Context context, List<Actor> mData, ActorItensClickListener listener) {
        this.context = context;
        this.mData = mData;
        this.ac = listener;
    }*/

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
                    //System.out.println("Aqui!!!!" + list.get(v.getId()));

                    //movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);
                }
            });
        }
    }
}