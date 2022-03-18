package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popcornandsodaonline.adapters.Categories_Details_Adapter;
import com.example.popcornandsodaonline.adapters.Movie_Productors_Adapter;
import com.example.popcornandsodaonline.models.Productor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser_Movies_Production extends AsyncTask<Void, Void, Integer> {

    private List<Productor> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    private Activity activity;
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView;

    String list_categories = "";

    public DataParser_Movies_Production(Context c, String jsonData, Activity activity,RecyclerView recyclerView){
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
            Movie_Productors_Adapter production_details_adapter = new Movie_Productors_Adapter(this.c, list);
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

                String name_productor = jsonObject.getString("name_productor");
                String cover_productor = jsonObject.getString("cover_productor");
                int id_productor = jsonObject.getInt("id_production");

                Productor productor =  new Productor();

                System.out.println("");
                System.out.println("Nome Produtor: " + name_productor);
                System.out.println("");
                System.out.println("Cover Produtor: " + cover_productor);
                System.out.println("");
                System.out.println("Id Produtor: " + id_productor);

                productor.setCover_productor(cover_productor);
                productor.setName_productor(name_productor);
                productor.setId_productor(id_productor);

                list.add(productor);

                resultado = 1;

            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
