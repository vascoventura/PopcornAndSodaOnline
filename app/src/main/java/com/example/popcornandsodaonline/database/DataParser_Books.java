package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.BookGridAdapter;
import com.example.popcornandsodaonline.adapters.MovieGridAdapter;
import com.example.popcornandsodaonline.models.Book;
import com.example.popcornandsodaonline.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser_Books extends AsyncTask<Void, Void, Integer> {
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
                int year = jsonObject.getInt("year_book");
                String description = jsonObject.getString("description_book");
                String cover_book = jsonObject.getString("cover_book");
                String background_book = jsonObject.getString("background_book");

                book = new Book();
                book.setId_book(id);
                book.setName_book(name);
                book.setRating_book(rating);
                book.setYear_book(year);
                book.setDescription_book(description);
                book.setCover_book(cover_book);
                book.setBackground_book(background_book);

                System.out.println("");
                System.out.println("Livro: " + String.valueOf(i + 1));
                System.out.println("");
                System.out.println(id);
                System.out.println(name);
                System.out.println(rating);
                System.out.println(year);
                System.out.println(description);
                System.out.println(cover_book);
                System.out.println(background_book);

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

