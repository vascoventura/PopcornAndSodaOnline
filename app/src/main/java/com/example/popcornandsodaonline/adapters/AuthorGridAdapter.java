package com.example.popcornandsodaonline.adapters;

import android.content.Context;
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
import com.example.popcornandsodaonline.models.Author;
import com.example.popcornandsodaonline.models.Book;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AuthorGridAdapter extends BaseAdapter {

    Context context;

    private ArrayList<Author> authorsList;

    LayoutInflater inflater;

    public AuthorGridAdapter(Context context, ArrayList<Author> authorsList) {
        this.context = context;
        this.authorsList = authorsList;
    }


    @Override
    public int getCount() {
        return authorsList.size();
    }

    @Override
    public Object getItem(int position) {
        return authorsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_authors_grid, null);

            TextView authorName = (TextView) view.findViewById(R.id.item_authors_grid_name);
            ImageView imageCover = (ImageView) view.findViewById(R.id.item_authors_grid_cover);

            final Author author = authorsList.get(position);
            try {
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + author.getCover_author());
                Glide.with(context).load(imageurl).into(imageCover);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            authorName.setText(author.getName_author());

            view.setTag(author);

        }

        return view;
    }
}
