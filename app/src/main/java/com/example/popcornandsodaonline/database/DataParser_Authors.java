package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.ActorGridAdapter;
import com.example.popcornandsodaonline.adapters.AuthorGridAdapter;
import com.example.popcornandsodaonline.models.Actor;
import com.example.popcornandsodaonline.models.Author;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser_Authors extends AsyncTask<Void, Void, Integer> {
    public static String ID_AUTHOR = "ID_AUTHOR";
    public static String NAME_AUTHOR = "NAME_AUTHOR";
    public static String COVER_AUTHOR = "COVER_AUTHOR";
    public static String NATIONALITY = "NATIONALITY";
    public static String BIRTHDAY_AUTHOR = "BIRTHDAY_AUTHOR";
    public static String DESCRIPTION_AUTHOR = "DESCRIPTION_AUTHOR";

    ProgressDialog pd;
    Context c;
    int resultado = 0;
    String jsonData;
    ArrayList<Author> authorsArrayList = new ArrayList<>();
    private Activity activity;
    private GridView gv;

    public DataParser_Authors(Context c, String jsonData, Activity activity, GridView gv){
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

            AuthorGridAdapter authorGridAdapter = new AuthorGridAdapter(this.c, authorsArrayList);
            gv.setAdapter(authorGridAdapter);
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            authorsArrayList.clear();
            Author author = null;

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id_author");
                String name = jsonObject.getString("name_author");
                String nationality = jsonObject.getString("nationality");
                String description = jsonObject.getString("description");
                String cover_author = jsonObject.getString("cover_author");

                author = new Author();
                author.setId_author(id);
                author.setName_author(name);
                author.setNationality_author(nationality);
                author.setDescription_author(description);
                author.setCover_author(cover_author);

                System.out.println("");
                System.out.println("Author: " + String.valueOf(i + 1));
                System.out.println("");
                System.out.println(id);
                System.out.println(name);
                System.out.println(nationality);
                System.out.println(description);
                System.out.println(cover_author);

                authorsArrayList.add(author);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}

