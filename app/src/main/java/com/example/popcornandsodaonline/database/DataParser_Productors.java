package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.ProductorGridAdapter;
import com.example.popcornandsodaonline.adapters.ShowGridAdapter;
import com.example.popcornandsodaonline.models.Productor;
import com.example.popcornandsodaonline.models.Show;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser_Productors extends AsyncTask<Void, Void, Integer> {
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

