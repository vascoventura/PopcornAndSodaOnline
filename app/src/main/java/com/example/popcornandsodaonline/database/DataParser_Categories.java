package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.Categories_Details_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class DataParser_Categories extends AsyncTask<Void, Void, Integer> {

    private List<String> list = new ArrayList<>();

    ProgressDialog pd;
    public Context c;
    int resultado = 0;
    String jsonData;
    private Activity activity;
    TextView textView;

    String list_categories = "";




    public DataParser_Categories(Context c, String jsonData, Activity activity, TextView textView){
        this.c = c;
        this.jsonData = jsonData;
        this.activity = activity;
        this.textView = textView;

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
            Toast.makeText(c, "Unable to Parse Categories", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c, "Categories Parsed Successfully", Toast.LENGTH_LONG).show();


            //Call Adapter
            Categories_Details_Adapter categories_details_adapter = new Categories_Details_Adapter(this.c, list);
            textView.setText(list_categories);
        }
    }

    private Integer parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            list.clear();

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                String category_name = jsonObject.getString("name_category_name");

                System.out.println("");
                System.out.println("Categoria: " + String.valueOf(i + 1));
                System.out.println("");
                System.out.println(category_name);

                list.add(category_name);

                resultado = 1;

            }

            for (int i = 0; i<list.size();i++){
                if(i == list.size()-1){
                    list_categories = list_categories + list.get(i);
                }else{
                    list_categories = list_categories + list.get(i) + ", ";
                }
            }

            System.out.println("list_categories: " + list_categories);

            return resultado;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}

