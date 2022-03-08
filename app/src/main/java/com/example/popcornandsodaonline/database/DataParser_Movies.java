package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.MovieGridAdapter;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.ui.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser_Movies extends AsyncTask<Void, Void, Integer> {
    ProgressDialog pd;
    Context c;
    int resultado = 0;
    String jsonData;
    ArrayList<Movie> moviesArrayList = new ArrayList<>();
    private Activity activity;
    private GridView gv;

    public DataParser_Movies(Context c, String jsonData, Activity activity, GridView gv){
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

            Movies movies = new Movies();
            movies.setMovieList(moviesArrayList);

            MovieGridAdapter movieGridAdapter = new MovieGridAdapter(this.c, moviesArrayList);
            gv.setAdapter(movieGridAdapter);
        }
    }


    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            moviesArrayList.clear();
            Movie movie = null;

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id_movie");
                String name = jsonObject.getString("name_movie");
                float rating = (float) jsonObject.getDouble("rating");
                int year = jsonObject.getInt("year_movie");
                String description = jsonObject.getString("description_movie");
                String trailer_link = jsonObject.getString("trailer_link");
                String cover_movie = jsonObject.getString("cover_movie");

                movie = new Movie();
                movie.setId_movie(id);
                movie.setName_movie(name);
                movie.setRating_movie(rating);
                movie.setYear_movie(year);
                movie.setDescription_movie(description);
                movie.setLink_trailer_filme(trailer_link);
                movie.setCover_image_movie(cover_movie);

                System.out.println("");
                System.out.println("Filme: " + String.valueOf(i + 1));
                System.out.println("");
                System.out.println(id);
                System.out.println(name);
                System.out.println(rating);
                System.out.println(year);
                System.out.println(description);
                System.out.println(trailer_link);
                System.out.println(cover_movie);

                moviesArrayList.add(movie);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultado;
    }
}
