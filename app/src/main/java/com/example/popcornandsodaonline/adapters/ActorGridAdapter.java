package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Actors.ID_ACTOR;
import static com.example.popcornandsodaonline.database.DataParser_Productors.ID_PRODUCTION;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.example.popcornandsodaonline.models.Actor;
import com.example.popcornandsodaonline.models.Book;
import com.example.popcornandsodaonline.ui.Details_Actors;
import com.example.popcornandsodaonline.ui.Details_Productions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ActorGridAdapter extends BaseAdapter{

    Context context;

    private ArrayList<Actor> actorsList;

    LayoutInflater inflater;

    public ActorGridAdapter(Context context, ArrayList<Actor> actorsList) {
        this.context = context;
        this.actorsList = actorsList;
    }

    @Override
    public int getCount() {
        return actorsList.size();
    }

    @Override
    public Object getItem(int position) {
        return actorsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_actors_grid, null);

            TextView actorName = (TextView) view.findViewById(R.id.item_actors_grid_name);
            ImageView imageCover = (ImageView) view.findViewById(R.id.item_actors_grid_cover);

            final Actor actor = actorsList.get(position);
            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + actor.getCover_actor());
                Glide.with(context).load(imageurl).into(imageCover);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            actorName.setText(actor.getName_actor());

            view.setTag(actor);

        }

        return view;
    }
}