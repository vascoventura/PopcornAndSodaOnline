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
import com.example.popcornandsodaonline.models.Book;
import com.example.popcornandsodaonline.models.Movie;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BookGridAdapter extends BaseAdapter {

    Context context;

    private ArrayList<Book> booksList;

    LayoutInflater inflater;

    public BookGridAdapter(Context context, ArrayList<Book> booksList) {
        this.context = context;
        this.booksList = booksList;
    }


    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public Object getItem(int position) {
        return booksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_movies_grid, null);

            TextView bookName = (TextView) view.findViewById(R.id.item_movies_grid_name);
            ImageView imageCover = (ImageView) view.findViewById(R.id.item_movies_grid_cover);

            final Book book = booksList.get(position);
            try{
                URL imageurl = new URL(ConnectionDb.CONECTIONIP + book.getCover_book());
                Glide.with(context).load(imageurl).into(imageCover);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            bookName.setText(book.getName_book());

            view.setTag(book);

        }

        return view;
    }
}