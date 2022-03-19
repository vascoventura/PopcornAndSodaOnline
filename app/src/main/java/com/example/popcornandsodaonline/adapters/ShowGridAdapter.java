package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Shows.ID_SHOW;

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
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.models.Show;
import com.example.popcornandsodaonline.ui.Details_Shows;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowGridAdapter extends BaseAdapter implements View.OnClickListener {

    Context context;

    private ArrayList<Show> showsList;

    LayoutInflater inflater;

    public ShowGridAdapter(Context context, ArrayList<Show> showsList) {
        this.context = context;
        this.showsList = showsList;
    }

    @Override
    public int getCount() {
        return showsList.size();
    }

    @Override
    public Object getItem(int position) {
        return showsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View view) {
        System.out.println("aqui!");
        System.out.println("ID DA SERIE: " + showsList.get(view.getId()).getId_show());

        Toast.makeText(this.context, "aqui!", Toast.LENGTH_LONG).show();
        int idShow = showsList.get(view.getId()).getId_show();
        Context context = view.getContext();
        Intent intent = new Intent();
        intent.setClass(context, Details_Shows.class);
        intent.putExtra(ID_SHOW, idShow);
        context.startActivity(intent);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_shows_grid, null);

            TextView nomeShow = (TextView) view.findViewById(R.id.item_shows_grid_name);
            ImageView imageCover = (ImageView) view.findViewById(R.id.item_shows_grid_cover);

            final Show show = showsList.get(position);
            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + show.getCover_show());
                Glide.with(context).load(imageurl).into(imageCover);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            nomeShow.setText(show.getName_show());

            view.setTag(show);

        }

        return view;
    }
}
