package com.example.popcornandsodaonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.models.Movie;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MovieGridAdapter extends BaseAdapter implements View.OnClickListener {

    Context context;

    private ArrayList<Movie> moviesList;

    LayoutInflater inflater;

    public MovieGridAdapter(Context context, ArrayList<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }


    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public Object getItem(int position) {
        return moviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View view) {
        System.out.println("aqui!");
        System.out.println("ID DO FILME: " + moviesList.get(view.getId()).getId_movie());

        Toast.makeText(this.context, "aqui!", Toast.LENGTH_LONG).show();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_movies_grid, null);

            TextView nomeFilme = (TextView) view.findViewById(R.id.item_movies_grid_name);
            ImageView imageCover = (ImageView) view.findViewById(R.id.item_movies_grid_cover);

            final Movie movie = moviesList.get(position);
            try{
                URL imageurl = new URL(movie.getCover_image_movie());
                Glide.with(context).load(imageurl).into(imageCover);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            nomeFilme.setText(movie.getName_movie());

            view.setTag(movie);

        }

        return view;
    }
}