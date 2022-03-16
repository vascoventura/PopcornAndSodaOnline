package com.example.popcornandsodaonline.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader_Movies extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAddress;
    ProgressDialog pd;
    GridView gv;
    String downloadData;
    Activity activity;
    ViewPager vp;


    public Downloader_Movies(Context c, Activity activity, String urlAdress, GridView gv) {
        this.c = c;
        this.activity = activity;
        this.urlAddress = urlAdress;
        this.gv = gv;
    }

    public Downloader_Movies(Context c, Activity activity, String urlAdress, ViewPager vp) {
        this.c = c;
        this.activity = activity;
        this.urlAddress = urlAdress;
        this.vp = vp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Fetch");
        pd.setMessage("Fetching... Please wait");
        pd.show();

    }

    @Override
    protected String doInBackground(Void... voids) {
        System.out.println(downloadData);
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(this.downloadData());

        pd.dismiss();

        s = this.downloadData;

        if(s==null){
            Toast.makeText(c,"Unable to Retrieve, null Returned!", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(c,"Success!", Toast.LENGTH_LONG).show();

            //call parser
            DataParser_Movies parser_movies = new DataParser_Movies(c, downloadData, this.activity, gv);

            parser_movies.execute();
        }
    }

    private String downloadData() {
        HttpURLConnection con = ConnectionDb.connect(urlAddress);
        System.out.println("CONEXAO " + con );
        if (con == null) {
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(con.getInputStream());
            System.out.println("INPUT STREAM " + inputStream.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;
            StringBuffer response = new StringBuffer();

            if (bufferedReader != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line+"\n");
                }

                bufferedReader.close();
            } else {
                return null;
            }
            this.downloadData = response.toString();
            System.out.println("downloader: " + downloadData);
            return downloadData;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
