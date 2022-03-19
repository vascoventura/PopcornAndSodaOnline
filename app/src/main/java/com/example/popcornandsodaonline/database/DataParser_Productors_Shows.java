package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popcornandsodaonline.adapters.Productor_Movies_Adapter;
import com.example.popcornandsodaonline.adapters.Productor_Shows_Adapter;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.models.Show;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser_Productors_Shows extends AsyncTask<Void, Void, Integer> {

    private List<Show> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    private Activity activity;
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView;

    String list_categories = "";

    public DataParser_Productors_Shows(Context c, String jsonData, Activity activity,RecyclerView recyclerView){
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
            Toast.makeText(c, "Unable to Parse Shows", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c, "Shows Parsed Successfully", Toast.LENGTH_LONG).show();


            //Call Adapter
            Productor_Shows_Adapter production_details_adapter = new Productor_Shows_Adapter(this.c, list);
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

                Show show =  new Show();

                show.setCover_show(cover_movie);
                show.setName_show(name_movie);
                show.setId_show(id_movie);

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

