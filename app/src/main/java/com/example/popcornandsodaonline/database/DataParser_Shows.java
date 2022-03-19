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

import com.example.popcornandsodaonline.adapters.MovieGridAdapter;
import com.example.popcornandsodaonline.adapters.ShowGridAdapter;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.models.Show;
import com.example.popcornandsodaonline.ui.Details_Movies;
import com.example.popcornandsodaonline.ui.Details_Shows;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser_Shows extends AsyncTask<Void, Void, Integer> {
    public static final String ID_SHOW = "ID_SHOW";
    public static final String NAME_SHOW = "NAME_SHOW";
    public static final String YEAR_SHOW = "YEAR_SHOW";
    public static final String YEAR_END_SHOW = "YEAR_END_SHOW";
    public static final String DESCRIPTION_SHOW = "DESCRIPTION_SHOW";
    public static final String TRAILER_SHOW = "TRAILER_SHOW";
    public static final String COVER_SHOW = "COVER_SHOW";
    public static final String RATING_SHOW = "RATING_SHOW";


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

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Show show = showsArrayList.get(i);
                    int id_show = show.getId_show();
                    String name_show = show.getName_show();
                    float rating = (float) show.getRating();
                    int year = show.getBegin_year();
                    int year_end = show.getEnd_year();
                    String description = show.getDescription_show();
                    String trailer_link = show.getTrailer_show();
                    String cover_show = show.getCover_show();


                    Toast.makeText(c, name_show, Toast.LENGTH_SHORT).show();

                    Context context = view.getContext();

                    Intent intent = new Intent();
                    Details_Shows dm = new Details_Shows();
                    intent.setClass(context, dm.getClass());

                    intent.putExtra(ID_SHOW, id_show);
                    intent.putExtra(NAME_SHOW, name_show);
                    intent.putExtra(YEAR_SHOW, String.valueOf(year));
                    intent.putExtra(YEAR_END_SHOW, String.valueOf(year_end));
                    intent.putExtra(DESCRIPTION_SHOW, description);
                    intent.putExtra(TRAILER_SHOW, trailer_link);
                    intent.putExtra(COVER_SHOW, cover_show);
                    intent.putExtra(RATING_SHOW, String.valueOf(rating));
                    context.startActivity(intent);
                }
            });
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
                int year_end = jsonObject.getInt("end_year_show");
                String description = jsonObject.getString("description_show");
                String trailer_link = jsonObject.getString("trailer_link");
                String cover_movie = jsonObject.getString("cover_show");

                show = new Show();
                show.setId_show(id);
                show.setName_show(name);
                show.setRating(rating);
                show.setBegin_year(year_begin);
                show.setEnd_year(year_end);
                show.setDescription_show(description);
                show.setTrailer_show(trailer_link);
                show.setCover_show(cover_movie);

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

