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

import androidx.viewpager.widget.ViewPager;

import com.example.popcornandsodaonline.adapters.MovieGridAdapter;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.ui.Details_Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//DataParser Movies (GridView)
public class DataParser_Movies extends AsyncTask<Void, Void, Integer> {
    public static final String ID_MOVIE = "ID_MOVIE";
    public static final String NAME_MOVIE = "NAME_MOVIE";
    public static final String YEAR_MOVIE = "YEAR_MOVIE";
    public static final String DESCRIPTION_MOVIE = "DESCRIPTION_MOVIE";
    public static final String TRAILER_MOVIE = "TRAILER_MOVIE";
    public static final String COVER_MOVIE = "COVER_MOVIE";
    public static final String RATING_MOVIE = "RATING_MOVIE";


    ProgressDialog pd;
    Context c;
    int resultado = 0;
    String jsonData;
    ArrayList<Movie> moviesArrayList = new ArrayList<>();
    Activity activity;
    GridView gv;

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

            MovieGridAdapter movieGridAdapter = new MovieGridAdapter(this.c, moviesArrayList);
            gv.setAdapter(movieGridAdapter);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Movie movie = moviesArrayList.get(i);
                    int id_movie = movie.getId_movie();
                    String name_movie = movie.getName_movie();
                    float rating = (float) movie.getRating_movie();
                    int year = movie.getYear_movie();
                    String description = movie.getDescription_movie();
                    String trailer_link = movie.getLink_trailer_filme();
                    String cover_movie = movie.getCover_image_movie();

                    Toast.makeText(c, name_movie, Toast.LENGTH_SHORT).show();

                    Context context = view.getContext();

                    Intent intent = new Intent();
                    Details_Movies dm = new Details_Movies();
                    intent.setClass(context, dm.getClass());

                    intent.putExtra(ID_MOVIE, id_movie);
                    intent.putExtra(NAME_MOVIE, name_movie);
                    intent.putExtra(YEAR_MOVIE, String.valueOf(year));
                    intent.putExtra(DESCRIPTION_MOVIE, description);
                    intent.putExtra(TRAILER_MOVIE, trailer_link);
                    intent.putExtra(COVER_MOVIE, cover_movie);
                    intent.putExtra(RATING_MOVIE, String.valueOf(rating));
                    context.startActivity(intent);
                }
            });
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