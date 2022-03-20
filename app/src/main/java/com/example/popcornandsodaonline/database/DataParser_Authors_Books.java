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
import com.example.popcornandsodaonline.adapters.Author_Books_Adapter;
import com.example.popcornandsodaonline.models.Author;
import com.example.popcornandsodaonline.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser_Authors_Books extends AsyncTask<Void, Void, Integer> {

    private List<Author> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    Activity activity;
    RecyclerView recyclerView;

    public DataParser_Authors_Books(Context c, String jsonData, Activity activity,RecyclerView recyclerView){
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
            Author_Books_Adapter production_details_adapter = new Author_Books_Adapter(this.c, list);
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

                String name_author = jsonObject.getString("name_author");
                String cover_author = jsonObject.getString("cover_author");
                int id_author = jsonObject.getInt("id_author");
                String year = (String) jsonObject.getString("birthday");
                String description = jsonObject.getString("description");
                String nationality = jsonObject.getString("nationality");


                Author author =  new Author();

                author.setId_author(id_author);
                author.setName_author(name_author);
                author.setCover_author(cover_author);
                author.setBirthday(year);
                author.setDescription_author(description);
                author.setNationality_author(nationality);

                list.add(author);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}