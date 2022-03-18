package com.example.popcornandsodaonline.adapters;

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
import com.example.popcornandsodaonline.models.Productor;
import com.example.popcornandsodaonline.ui.Details_Productions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProductorGridAdapter extends BaseAdapter implements View.OnClickListener {

    Context context;

    private ArrayList<Productor> productorsList;

    LayoutInflater inflater;

    public ProductorGridAdapter(Context context, ArrayList<Productor> productorsList) {
        this.context = context;
        this.productorsList = productorsList;
    }


    @Override
    public int getCount() {
        return productorsList.size();
    }

    @Override
    public Object getItem(int position) {
        return productorsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View view) {

        System.out.println("ID DO PRODUTOR: " + productorsList.get(view.getId()).getId_productor());

        int idProductor = productorsList.get(view.getId()).getId_productor();
        Context context = view.getContext();
        Intent intent = new Intent();
        intent.setClass(context, Details_Productions.class);
        intent.putExtra(ID_PRODUCTION, idProductor);
        context.startActivity(intent);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_productors_grid, null);

            TextView actorName = (TextView) view.findViewById(R.id.item_productors_grid_name);
            ImageView imageCover = (ImageView) view.findViewById(R.id.item_productors_grid_cover);

            final Productor productor = productorsList.get(position);
            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + productor.getCover_productor());
                Glide.with(context).load(imageurl).into(imageCover);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            actorName.setText(productor.getName_productor());

            view.setTag(productor);

        }

        return view;
    }
}
