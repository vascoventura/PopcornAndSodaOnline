package com.example.popcornandsodaonline.database;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionDb {
    public static String CONECTIONIP = "https://popcornsoda.000webhostapp.com/";
    public static String PHP_DIRECTORY = "";
    public static final String IMAGES_DIRECTORY = "images/";

    public static String PHP_LOGIN_FILE = "login.php";
    public static String PHP_SIGNUP_FILE = "signup.php";
    public static final String PHP_MOVIES_FILE = "movies_select.php";
    public static final String PHP_ACTORS_FILE = "actors_select.php";
    public static final String PHP_SHOWS_FILE = "tvshows_select.php";
    public static final String PHP_BOOKS_FILE = "books_select.php";
    public static final String PHP_AUTHORS_FILE = "authors_select.php";
    public static final String PHP_PRODUCTORS_FILE = "productors_select.php";
    public static final String PHP_BACKGROUND_MOVIES_FILE = "movie_back_select.php/?id_filme=";
    public static final String PHP_CATEGORY_NAME_MOVIE_FILE = "category_name_movie.php/?id_filme=";
    public static final String PHP_PRODUCTOR_MOVIE_FILE = "movie_productors_select.php/?id_filme=";

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
