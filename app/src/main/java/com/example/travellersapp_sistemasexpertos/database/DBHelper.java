package com.example.travellersapp_sistemasexpertos.database;

import android.os.AsyncTask;

import com.example.travellersapp_sistemasexpertos.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jay on 06-06-2017.
 */

public class DBHelper  {

    public static ArrayList<User> USERS;

    public static String apiUrl = "https://loaiza4ever.000webhostapp.com/TravellersApi/api/";

    public DBHelper(){

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAllUsers();
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

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("id");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String name = jsonObject.getString("name");

            User user = new User(id, username, password, name);

            USERS.add(user);

        }
    }


}