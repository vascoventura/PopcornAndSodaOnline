package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popcornandsodaonline.adapters.Book_Authors_Adapter;
import com.example.popcornandsodaonline.adapters.Movie_Actors_Adapter;
import com.example.popcornandsodaonline.models.Actor;
import com.example.popcornandsodaonline.models.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser_Books_Authors extends AsyncTask<Void, Void, Integer> {

    private List<Book> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    Activity activity;
    RecyclerView recyclerView;


    public DataParser_Books_Authors(Context c, String jsonData, Activity activity,RecyclerView recyclerView){
        this.c = c;
        this.jsonData = jsonData;
        this.activity = activity;
        this.recyclerView = recyclerView;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseData();
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
            Toast.makeText(c, "Unable to Parse Production", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c, "Production Parsed Successfully", Toast.LENGTH_LONG).show();


            //Call Adapter
            Book_Authors_Adapter actors_details_adapter = new Book_Authors_Adapter(this.c, list);
            recyclerView.setAdapter(actors_details_adapter);
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            list.clear();


            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                String name_book = jsonObject.getString("name_book");
                String cover_book = jsonObject.getString("cover_book");
                String back_book = jsonObject.getString("background_book");
                int id_book = jsonObject.getInt("id_book");
                String description_book = jsonObject.getString("description_book");
                String year_book = (String) jsonObject.getString("year_book");
                float rating = (float) jsonObject.getDouble("rating");

                Book book =  new Book();

                book.setName_book(name_book);
                book.setCover_book(cover_book);
                book.setId_book(id_book);
                book.setBackground_book(back_book);
                book.setDescription_book(description_book);
                book.setYear(year_book);
                book.setRating_book(rating);

                list.add(book);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
