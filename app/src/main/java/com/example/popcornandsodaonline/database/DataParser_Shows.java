package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.MovieGridAdapter;
import com.example.popcornandsodaonline.adapters.ShowGridAdapter;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.models.Show;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser_Shows extends AsyncTask<Void, Void, Integer> {
    ProgressDialog pd;
    Context c;
    int resultado = 0;
    String jsonData;
    ArrayList<Show> showsArrayList = new ArrayList<>();
    private Activity activity;
    private GridView gv;

    public DataParser_Shows(Context c, String jsonData, Activity activity, GridView gv){
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

            ShowGridAdapter showGridAdapter = new ShowGridAdapter(this.c, showsArrayList);
            gv.setAdapter(showGridAdapter);
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            showsArrayList.clear();
            Show show = null;

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id_show");
                String name = jsonObject.getString("name_show");
                float rating = (float) jsonObject.getDouble("rating");
                int year_begin = jsonObject.getInt("begin_year_show");
//                int year_end = jsonObject.getInt("end_year_show");
                String description = jsonObject.getString("description_show");
                String trailer_link = jsonObject.getString("trailer_link");
                String cover_movie = jsonObject.getString("cover_show");

                show = new Show();
                show.setId_show(id);
                show.setName_show(name);
                show.setRating(rating);
                show.setBegin_year(year_begin);
  //              show.setEnd_year(year_end);
                show.setDescription_show(description);
                show.setTrailer_show(trailer_link);
                show.setCover_show(cover_movie);

                System.out.println("");
                System.out.println("Serie: " + String.valueOf(i + 1));
                System.out.println("");
                System.out.println(id);
                System.out.println(name);
                System.out.println(rating);
                System.out.println(year_begin);
    //          System.out.println(year_end);
                System.out.println(description);
                System.out.println(trailer_link);
                System.out.println(cover_movie);

                showsArrayList.add(show);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}

