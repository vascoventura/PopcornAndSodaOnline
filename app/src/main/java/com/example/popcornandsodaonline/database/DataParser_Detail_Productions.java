package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.example.popcornandsodaonline.adapters.SliderAdapter_Details_Movies;
import com.example.popcornandsodaonline.adapters.SliderAdapter_Details_Productions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser_Detail_Productions extends AsyncTask<Void, Void, Integer> {

    private List<String> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    private Activity activity;
    ViewPager sliderpager;

    public DataParser_Detail_Productions(Context c, String jsonData, Activity activity, ViewPager sliderpager){
        this.c = c;
        this.jsonData = jsonData;
        this.activity = activity;
        this.sliderpager = sliderpager;
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
            Toast.makeText(c, "Unable to Parse", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c, "Parsed Successfully", Toast.LENGTH_LONG).show();

            SliderAdapter_Details_Productions sliderAdapter_details_productions = new SliderAdapter_Details_Productions(this.c, list);
            sliderpager.setAdapter(sliderAdapter_details_productions);
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            list.clear();

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                String back_production = jsonObject.getString("production_background");
                list.add(back_production);

                resultado = 1;
            }

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
