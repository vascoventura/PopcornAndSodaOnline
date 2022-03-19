package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popcornandsodaonline.adapters.Actor_Shows_Adapter;
import com.example.popcornandsodaonline.adapters.Productor_Movies_Adapter;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.models.Show;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser_Actors_Shows extends AsyncTask<Void, Void, Integer> {

    private List<Show> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    private Activity activity;
    RecyclerView recyclerView;


    public DataParser_Actors_Shows(Context c, String jsonData, Activity activity,RecyclerView recyclerView){
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
            Actor_Shows_Adapter production_details_adapter = new Actor_Shows_Adapter(this.c, list);
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

                String name_movie = jsonObject.getString("name_show");
                String cover_movie = jsonObject.getString("cover_show");
                int id_movie = jsonObject.getInt("id_show");
                float rating = (float) jsonObject.getDouble("rating");
                int year = jsonObject.getInt("begin_year_show");
                int end_year = jsonObject.getInt("end_year_show");
                String description = jsonObject.getString("description_show");
                String trailer_link = jsonObject.getString("trailer_link");

                Show show =  new Show();

                show.setCover_show(cover_movie);
                show.setName_show(name_movie);
                show.setId_show(id_movie);
                show.setRating(rating);
                show.setBegin_year(year);
                show.setEnd_year(end_year);
                show.setDescription_show(description);
                show.setTrailer_show(trailer_link);

                list.add(show);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}

