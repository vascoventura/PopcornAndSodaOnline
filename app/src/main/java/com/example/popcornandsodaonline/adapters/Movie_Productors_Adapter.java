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
import com.example.popcornandsodaonline.models.Productor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Movie_Productors_Adapter extends RecyclerView.Adapter<Movie_Productors_Adapter.MyViewHolder> {

    Context context;
    List<Productor> list;
    //MovieItensClickListener movieItemClickListener;

    /*public MovieAdapter(Context context, List<Productor> mData, MovieItensClickListener listener) {
        this.context = context;
        this.mData = mData;
        movieItemClickListener = listener;
    }*/

    public Movie_Productors_Adapter(Context context, List<Productor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_list_movie_production, viewGroup, false);
        return new MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.productorTitle.setText(list.get(i).getName_productor());
        try {
            URL imageurl = new URL(ConnectionDb.CONECTIONIP + list.get(i).getCover_productor());
            Glide.with(context).load(imageurl).into(myViewHolder.ImgProductor);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView productorTitle;
        private ImageView ImgProductor;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            productorTitle = itemView.findViewById(R.id.textView_movie_production);
            ImgProductor = itemView.findViewById(R.id.imageView_movie_production);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);
                }
            });
        }
    }
}