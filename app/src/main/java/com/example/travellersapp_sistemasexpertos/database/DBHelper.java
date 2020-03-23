package com.example.travellersapp_sistemasexpertos.database;

import android.os.AsyncTask;

import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
import com.example.travellersapp_sistemasexpertos.domain.ReservationPackage;
import com.example.travellersapp_sistemasexpertos.domain.TouristCompany;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBHelper  {

    public static ArrayList<User> USERS;
    public static ArrayList<Airport> AIRPORTS;
    public static ArrayList<Hotel> HOTELS;
    public static ArrayList<ReservationPackage> RESERVATIONS;
    public static ArrayList<TouristCompany> TOURIST_COMPANIES;
    public static ArrayList<TravelPackage> TRAVEL_PACKAGES;

    public static String apiUrl = "https://loaiza4ever.000webhostapp.com/TravellersApi/api/";

    public DBHelper(){

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAllUsers();
                    TRAVEL_PACKAGES = new ArrayList<>();
                    Data.fillList();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();

    }

    public static void getAllUsers() throws JSONException {

        USERS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBUsers.URLRead(), params);

        /*for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);

            int id = jsonObject.getInt("id");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String name = jsonObject.getString("name");
            String lastname = jsonObject.getString("lastname");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");

            User user = new User(id, username, password, name, lastname, email, phone);

            USERS.add(user);

        }*/

        int id = 1;
        String username = "juliojose3000";
        String password = "123";
        String name = "Julio";
        String lastname = "Segura";
        String email = "juliojose3000@gmail.com";
        String phone = "87349630";

        User user = new User(id, username, password, name, lastname, email, phone);

        USERS.add(user);


    }


    public static int insertUser(String name, String lastname, String email, String phone, String username, String password){

        int code = 0;

        Map<String, String> params = new HashMap<>();

        params.put("name",name);
        params.put("lastname",lastname);
        params.put("email",email);
        params.put("phone",phone);
        params.put("username",username);
        params.put("password",password);

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        try {
            code = httpJsonParser.sendJson(DBUsers.URLCreate(), params);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }


}