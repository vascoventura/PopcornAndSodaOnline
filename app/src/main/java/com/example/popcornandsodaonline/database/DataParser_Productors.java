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
import com.example.popcornandsodaonline.adapters.ProductorGridAdapter;
import com.example.popcornandsodaonline.adapters.ShowGridAdapter;
import com.example.popcornandsodaonline.models.Movie;
import com.example.popcornandsodaonline.models.Productor;
import com.example.popcornandsodaonline.models.Show;
import com.example.popcornandsodaonline.ui.Details_Movies;
import com.example.popcornandsodaonline.ui.Details_Productions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


//DataParser para a gridView dos Produtores

public class DataParser_Productors extends AsyncTask<Void, Void, Integer> {
    public static final String ID_PRODUCTION = "ID_PRODUCTION";
    public static final String NAME_PRODUCTION = "NAME_PRODUCTION";
    public static final String YEAR_BIRTHDAY = "YEAR_PRODUCTION";
    public static final String NATIONALITY_PRODUCTION = "NATIONALITY_PRODUCTION";
    public static final String DESCRIPTION_PRODUCTION = "DESCRIPTION_PRODUCTION";
    public static final String COVER_PRODUCTION = "COVER_PRODUCTION";

    ProgressDialog pd;
    Context c;
    int resultado = 0;
    String jsonData;
    ArrayList<Productor> productorsArrayList = new ArrayList<>();
    private Activity activity;
    private GridView gv;

    public DataParser_Productors(Context c, String jsonData, Activity activity, GridView gv){
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

            ProductorGridAdapter productorGridAdapter = new ProductorGridAdapter(this.c, productorsArrayList);
            gv.setAdapter(productorGridAdapter);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Productor productor = productorsArrayList.get(i);
                    long id_productor = productor.getId_productor();
                    String name_productor = productor.getName_productor();
                   // String birthday = productor.getgetYear_movie();
                    String description = productor.getDescription();
                    String cover_productor = productor.getCover_productor();
                    String nationality = productor.getNationality();

                    Toast.makeText(c, name_productor, Toast.LENGTH_SHORT).show();

                    Context context = view.getContext();

                    Intent intent = new Intent();
                    Details_Productions dp = new Details_Productions();
                    intent.setClass(context, dp.getClass());

                    intent.putExtra(ID_PRODUCTION, id_productor);
                    intent.putExtra(NAME_PRODUCTION, name_productor);
                    //intent.putExtra(YEAR_MOVIE, String.valueOf(year));
                    intent.putExtra(DESCRIPTION_PRODUCTION, description);
                    intent.putExtra(COVER_PRODUCTION, cover_productor);
                    intent.putExtra(NATIONALITY_PRODUCTION, nationality);
                    context.startActivity(intent);
                }
            });
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            productorsArrayList.clear();
            Productor productor = null;

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id_production");
                String name = jsonObject.getString("name_productor");
                String nationality = jsonObject.getString("nationality");
                String description = jsonObject.getString("description");
                String cover_productor = jsonObject.getString("cover_productor");

                productor = new Productor();
                productor.setId_productor(id);
                productor.setName_productor(name);
                productor.setNationality(nationality);
                productor.setDescription(description);
                productor.setCover_productor(cover_productor);


                System.out.println("");
                System.out.println("Productor: " + String.valueOf(i + 1));
                System.out.println("");
                System.out.println(id);
                System.out.println(name);
                System.out.println(nationality);
                System.out.println(description);
                System.out.println(cover_productor);


                productorsArrayList.add(productor);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}

