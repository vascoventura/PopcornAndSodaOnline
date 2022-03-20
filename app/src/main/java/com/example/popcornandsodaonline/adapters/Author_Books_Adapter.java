package com.example.popcornandsodaonline.adapters;

import static com.example.popcornandsodaonline.database.DataParser_Authors.BIRTHDAY_AUTHOR;
import static com.example.popcornandsodaonline.database.DataParser_Authors.COVER_AUTHOR;
import static com.example.popcornandsodaonline.database.DataParser_Authors.DESCRIPTION_AUTHOR;
import static com.example.popcornandsodaonline.database.DataParser_Authors.ID_AUTHOR;
import static com.example.popcornandsodaonline.database.DataParser_Authors.NAME_AUTHOR;
import static com.example.popcornandsodaonline.database.DataParser_Authors.NATIONALITY;

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
import com.example.popcornandsodaonline.models.Author;
import com.example.popcornandsodaonline.ui.Details_Authors;
import com.example.popcornandsodaonline.ui.Details_Books;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Author_Books_Adapter extends RecyclerView.Adapter<Author_Books_Adapter.MyViewHolder>{

    Context context;
    List<Author> list;

    public Author_Books_Adapter(Context context, List<Author> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Author_Books_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_list_movie_production, viewGroup, false);
        return new Author_Books_Adapter.MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull Author_Books_Adapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.showTitle.setText(list.get(i).getName_author());
        try {
            URL imageurl = new URL(ConnectionDb.CONECTIONIP + list.get(i).getCover_author());
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

                    int idAuthor = (int) list.get(getAdapterPosition()).getId_author();
                    String capaAuthor = list.get(getAdapterPosition()).getCover_author();
                    String descricaoAuthor = list.get(getAdapterPosition()).getDescription_author();
                    String nameAuthor = list.get(getAdapterPosition()).getName_author();
                    String year = list.get(getAdapterPosition()).getBirthday();
                    String nationality = list.get(getAdapterPosition()).getNationality_author();

                    Context context = v.getContext();
                    Intent intent = new Intent();
                    intent.setClass(context, Details_Authors.class);
                    intent.putExtra(ID_AUTHOR, idAuthor);
                    intent.putExtra(COVER_AUTHOR, capaAuthor);
                    intent.putExtra(NAME_AUTHOR,nameAuthor);
                    intent.putExtra(DESCRIPTION_AUTHOR, descricaoAuthor);
                    intent.putExtra(BIRTHDAY_AUTHOR, year);
                    intent.putExtra(NATIONALITY, nationality);

                    context.startActivity(intent);
                }
            });
        }
    }
}