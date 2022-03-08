package com.example.popcornandsodaonline.database;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionDb {
    public static String CONECTIONIP = "http://192.168.85.37/";
    public static String PHP_DIRECTORY = "popcornsodaDBPHP/";
    public static final String IMAGES_DIRECTORY = "images/";

    public static String PHP_LOGIN_FILE = "login.php";
    public static String PHP_SIGNUP_FILE = "signup.php";
    public static final String PHP_MOVIES_FILE = "movies_select.php";
    public static final String PHP_ACTORS_FILE = "actors_select_php";



    public static HttpURLConnection connect(String urlAddress){

        try {
            URL url = new URL(urlAddress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //SET PROPRIETIES
            con.setRequestMethod("GET");
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setDoInput(true);

            return con;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
