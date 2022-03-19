package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Movies.ID_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Movies.TRAILER_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Movies.YEAR_MOVIE;
import static com.example.popcornandsodaonline.database.DataParser_Shows.COVER_SHOW;
import static com.example.popcornandsodaonline.database.DataParser_Shows.DESCRIPTION_SHOW;
import static com.example.popcornandsodaonline.database.DataParser_Shows.ID_SHOW;
import static com.example.popcornandsodaonline.database.DataParser_Shows.NAME_SHOW;
import static com.example.popcornandsodaonline.database.DataParser_Shows.RATING_SHOW;
import static com.example.popcornandsodaonline.database.DataParser_Shows.YEAR_END_SHOW;

import android.app.Activity;
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
import com.example.popcornandsodaonline.models.Show;
import com.example.popcornandsodaonline.ui.Details_Shows;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Productor_Shows_Adapter extends RecyclerView.Adapter<Productor_Shows_Adapter.MyViewHolder>{

    Context context;
    List<Show> list;

    public Productor_Shows_Adapter(Context context, List<Show> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Productor_Shows_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_list_movie_production, viewGroup, false);
        return new Productor_Shows_Adapter.MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull Productor_Shows_Adapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.showTitle.setText(list.get(i).getName_show());
        try {
            URL imageurl = new URL(ConnectionDb.CONECTIONIP + list.get(i).getCover_show());
            Glide.with(context).load(imageurl).into(myViewHolder.ImgShow);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView showTitle;
        private ImageView ImgShow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            showTitle = itemView.findViewById(R.id.textView_movie_production);
            ImgShow = itemView.findViewById(R.id.imageView_movie_production);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idShow = (int) list.get(getAdapterPosition()).getId_show();
                    String capaShow = list.get(getAdapterPosition()).getCover_show();
                    String descricaoShow = list.get(getAdapterPosition()).getDescription_show();
                    String nameShow = list.get(getAdapterPosition()).getName_show();
                    float rating = (float) list.get(getAdapterPosition()).getRating();
                    int year = list.get(getAdapterPosition()).getBegin_year();
                    int end_year = list.get(getAdapterPosition()).getEnd_year();
                    String trailer = list.get(getAdapterPosition()).getTrailer_show();
                    Context context = v.getContext();
                    Intent intent = new Intent();
                    intent.setClass(context, Details_Shows.class);
                    intent.putExtra(ID_SHOW, idShow);
                    intent.putExtra(COVER_SHOW, capaShow);
                    intent.putExtra(NAME_SHOW,nameShow);
                    intent.putExtra(DESCRIPTION_SHOW, descricaoShow);
                    intent.putExtra(RATING_SHOW, String.valueOf(rating));
                    intent.putExtra(YEAR_MOVIE, String.valueOf(year));
                    intent.putExtra(YEAR_END_SHOW, String.valueOf(end_year));
                    intent.putExtra(TRAILER_MOVIE, trailer);

                    context.startActivity(intent);
                }
            });
        }
    }
}
