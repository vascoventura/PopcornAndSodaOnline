package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Productors.COVER_PRODUCTION;
import static com.example.popcornandsodaonline.database.DataParser_Productors.DESCRIPTION_PRODUCTION;
import static com.example.popcornandsodaonline.database.DataParser_Productors.ID_PRODUCTION;
import static com.example.popcornandsodaonline.database.DataParser_Productors.NAME_PRODUCTION;
import static com.example.popcornandsodaonline.database.DataParser_Productors.NATIONALITY_PRODUCTION;
import static com.example.popcornandsodaonline.database.DataParser_Productors.YEAR_BIRTHDAY;

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
import com.example.popcornandsodaonline.models.Productor;
import com.example.popcornandsodaonline.ui.Details_Productions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Movie_Productors_Adapter extends RecyclerView.Adapter<Movie_Productors_Adapter.MyViewHolder>{

    Context context;
    List<Productor> list;

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
                    int idProductor = (int) list.get(getAdapterPosition()).getId_productor();
                    String name = list.get(getAdapterPosition()).getName_productor();
                    String nationality = list.get(getAdapterPosition()).getNationality();
                    String description = list.get(getAdapterPosition()).getDescription();
                    String cover_productor = list.get(getAdapterPosition()).getCover_productor();;
                    String birthday = (String) list.get(getAdapterPosition()).getBirthday_productor();

                    Context context = v.getContext();
                    Intent intent = new Intent();
                    intent.setClass(context, Details_Productions.class);
                    intent.putExtra(ID_PRODUCTION, idProductor);
                    intent.putExtra(NAME_PRODUCTION, name);
                    intent.putExtra(DESCRIPTION_PRODUCTION, description);
                    intent.putExtra(NATIONALITY_PRODUCTION, nationality);
                    intent.putExtra(YEAR_BIRTHDAY, birthday);
                    intent.putExtra(COVER_PRODUCTION, cover_productor);

                    context.startActivity(intent);
                }
            });
        }
    }
}