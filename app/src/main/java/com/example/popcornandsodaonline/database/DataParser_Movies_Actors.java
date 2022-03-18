package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popcornandsodaonline.adapters.Movie_Actors_Adapter;
import com.example.popcornandsodaonline.models.Actor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser_Movies_Actors extends AsyncTask<Void, Void, Integer> {

    private List<Actor> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    private Activity activity;
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView;

    String list_categories = "";

    public DataParser_Movies_Actors(Context c, String jsonData, Activity activity,RecyclerView recyclerView){
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
            Movie_Actors_Adapter actors_details_adapter = new Movie_Actors_Adapter(this.c, list);
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

                String name_actor = jsonObject.getString("name_actor");
                String cover_actor = jsonObject.getString("cover_actor");
                int id_actor = jsonObject.getInt("id_actor");

                Actor actor =  new Actor();

                System.out.println("");
                System.out.println("Nome Actor: " + name_actor);
                System.out.println("");
                System.out.println("Cover Actor: " + cover_actor);

                actor.setName_actor(name_actor);
                actor.setCover_actor(cover_actor);
                actor.setId_actor(id_actor);

                list.add(actor);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
