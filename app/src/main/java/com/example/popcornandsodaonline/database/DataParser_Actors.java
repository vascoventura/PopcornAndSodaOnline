package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.ActorGridAdapter;
import com.example.popcornandsodaonline.models.Actor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser_Actors extends AsyncTask<Void, Void, Integer> {
    ProgressDialog pd;
    Context c;
    int resultado = 0;
    String jsonData;
    ArrayList<Actor> actorsArrayList = new ArrayList<>();
    private Activity activity;
    private GridView gv;

    public DataParser_Actors(Context c, String jsonData, Activity activity, GridView gv){
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

            ActorGridAdapter actorGridAdapter = new ActorGridAdapter(this.c, actorsArrayList);
            gv.setAdapter(actorGridAdapter);
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            actorsArrayList.clear();
            Actor actor = null;

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id_actor");
                String name = jsonObject.getString("name_actor");
                String nationality = jsonObject.getString("nationality");
                String description = jsonObject.getString("actor_description");
                String cover_actor = jsonObject.getString("cover_actor");

                actor = new Actor();
                actor.setId_actor(id);
                actor.setName_actor(name);
                actor.setNationality(nationality);
                actor.setDescription(description);
                actor.setCover_actor(cover_actor);

                System.out.println("");
                System.out.println("Actor: " + String.valueOf(i + 1));
                System.out.println("");
                System.out.println(id);
                System.out.println(name);
                System.out.println(nationality);
                System.out.println(description);
                System.out.println(cover_actor);

                actorsArrayList.add(actor);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}

