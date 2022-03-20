package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.BookGridAdapter;
import com.example.popcornandsodaonline.adapters.MovieGridAdapter;
import com.example.popcornandsodaonline.models.Book;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.ui.Details_Books;
import com.example.popcornandsodaonline.ui.Details_Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser_Books extends AsyncTask<Void, Void, Integer> {
    public static final String ID_BOOK = "ID_BOOK";
    public static final String RATING_BOOK = "RATING_BOOK";
    public static final String NAME_BOOK = "NAME_BOOK";
    public static final String YEAR_BOOK = "YEAR_BOOK";
    public static final String DESCRIPTION_BOOK = "DESCRIPTION_BOOK";
    public static final String COVER_BOOK = "COVER_BOOK";
    public static final String BACKGROUND_BOOK = "BACKGROUND_BOOK";


    ProgressDialog pd;
    Context c;
    int resultado = 0;
    String jsonData;
    ArrayList<Book> booksArrayList = new ArrayList<>();
    private Activity activity;
    private GridView gv;

    public DataParser_Books(Context c, String jsonData, Activity activity, GridView gv){
        this.c = c;
        this.jsonData = jsonData;
        this.activity = activity;
        this.gv = gv;

    }
    @Override
    protected Integer doInBackground(Void... voids) {
        this.parseData();
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing... Please Wait!");
        pd.show();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        result = this.resultado;
        pd.dismiss();
        if(result==0){
            Toast.makeText(c, "Unable to Parse", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c, "Parsed Successfully", Toast.LENGTH_LONG).show();


            BookGridAdapter bookGridAdapter = new BookGridAdapter(this.c, booksArrayList);
            gv.setAdapter(bookGridAdapter);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Book book = booksArrayList.get(i);
                    int id_book = book.getId_book();
                    String name_book = book.getName_book();
                    float rating = (float) book.getRating_book();
                    String year = book.getYear();
                    String description = book.getDescription_book();
                    String cover_book = book.getCover_book();
                    String background_book = book.getBackground_book();

                    Toast.makeText(c, name_book, Toast.LENGTH_SHORT).show();

                    Context context = view.getContext();

                    Intent intent = new Intent();
                    Details_Books dm = new Details_Books();
                    intent.setClass(context, dm.getClass());

                    intent.putExtra(ID_BOOK, id_book);
                    intent.putExtra(NAME_BOOK, name_book);
                    intent.putExtra(YEAR_BOOK, String.valueOf(year));
                    intent.putExtra(DESCRIPTION_BOOK, description);
                    intent.putExtra(COVER_BOOK, cover_book);
                    intent.putExtra(BACKGROUND_BOOK, background_book);
                    intent.putExtra(RATING_BOOK, String.valueOf(rating));
                    context.startActivity(intent);
        }});
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            booksArrayList.clear();
            Book book = null;

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id_book");
                String name = jsonObject.getString("name_book");
                float rating = (float) jsonObject.getDouble("rating");
                String year = jsonObject.getString("year_book");
                String description = jsonObject.getString("description_book");
                String cover_book = jsonObject.getString("cover_book");
                String background_book = jsonObject.getString("background_book");

                book = new Book();
                book.setId_book(id);
                book.setName_book(name);
                book.setRating_book(rating);
                book.setYear(year);
                book.setDescription_book(description);
                book.setCover_book(cover_book);
                book.setBackground_book(background_book);

                booksArrayList.add(book);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}

