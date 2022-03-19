package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popcornandsodaonline.adapters.Actor_Movies_Adapter;
import com.example.popcornandsodaonline.adapters.Productor_Movies_Adapter;
import com.example.popcornandsodaonline.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser_Actors_Movies extends AsyncTask<Void, Void, Integer> {

    private List<Movie> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    private Activity activity;
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView;

    String list_categories = "";

    public DataParser_Actors_Movies(Context c, String jsonData, Activity activity,RecyclerView recyclerView){
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
            Actor_Movies_Adapter production_details_adapter = new Actor_Movies_Adapter(this.c, list);
            recyclerView.setAdapter(production_details_adapter);
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            list.clear();


            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                String name_movie = jsonObject.getString("name_movie");
                String cover_movie = jsonObject.getString("cover_movie");
                int id_movie = jsonObject.getInt("id_movie");
                float rating = (float) jsonObject.getDouble("rating");
                int year = jsonObject.getInt("year_movie");
                String description = jsonObject.getString("description_movie");
                String trailer_link = jsonObject.getString("trailer_link");

                Movie movie =  new Movie();

                movie.setCover_image_movie(cover_movie);
                movie.setName_movie(name_movie);
                movie.setId_movie(id_movie);
                movie.setRating_movie(rating);
                movie.setYear_movie(year);
                movie.setDescription_movie(description);
                movie.setLink_trailer_filme(trailer_link);

                list.add(movie);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
