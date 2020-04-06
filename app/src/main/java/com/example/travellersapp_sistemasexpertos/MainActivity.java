package com.example.travellersapp_sistemasexpertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.activities.BaseActivity;
import com.example.travellersapp_sistemasexpertos.activities.Login;
import com.example.travellersapp_sistemasexpertos.activities.MainInterface;
import com.example.travellersapp_sistemasexpertos.activities.SearchTravel;
import com.example.travellersapp_sistemasexpertos.activities.SingUp;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
import com.example.travellersapp_sistemasexpertos.domain.Image;
import com.example.travellersapp_sistemasexpertos.domain.ReservationPackage;
import com.example.travellersapp_sistemasexpertos.domain.TouristCompany;
import com.example.travellersapp_sistemasexpertos.domain.TouristDestination;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;
import com.example.travellersapp_sistemasexpertos.utilities.Data;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends BaseActivity {

    private static Thread thread;

    public static ArrayList<User> USERS;
    public static ArrayList<ReservationPackage> RESERVATIONS;
    public static ArrayList<TravelPackage> TRAVEL_PACKAGES;
    public static ArrayList<Image> IMAGES;
    public static ArrayList<Hotel> HOTELS;
    public static ArrayList<Airport> AIRPORTS;
    public static ArrayList<TouristCompany> TOURISTCOMPANIES;
    public static ArrayList<TouristDestination> TOURISTDESTINATIONS;
    public static boolean isAllDataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        if(!isThereInternetAccess()){
            Toast.makeText(MainActivity.this,"Compruebe su conexión a internet e intente de nuevo",Toast.LENGTH_SHORT).show();
        }

        thread = new Thread(){
            public void run(){
                try {

                    USERS = DBHelper.getAllUsers();
                    HOTELS= DBHelper.getAllHotels();
                    AIRPORTS=DBHelper.getAllAirports();
                    TOURISTCOMPANIES=DBHelper.getAllTouristCompany();
                    IMAGES = DBHelper.getAllImages();
                    TOURISTDESTINATIONS=DBHelper.getAllTouristDestination();
                    TRAVEL_PACKAGES = DBHelper.getAllTravelPackage();
                    RESERVATIONS = DBHelper.getAllReservations();
                    isAllDataLoaded = true;

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

        //Data.fillList();
        //Data.getResults("",50,"", "");
    }

    public static void loadDataFromDB(final Context context){

        try {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... voids) {

                    try {
                        USERS = DBHelper.getAllUsers();
                        HOTELS= DBHelper.getAllHotels();
                        AIRPORTS=DBHelper.getAllAirports();
                        IMAGES = DBHelper.getAllImages();
                        TRAVEL_PACKAGES = DBHelper.getAllTravelPackage();
                        RESERVATIONS = DBHelper.getAllReservations();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void login(View v){

        Intent i = new Intent(this, Login.class);

        i.putExtra("whereIGo", "searchTravel");

        startActivity(i);

    }

    public void continues(View v){

        Intent i = new Intent(this, SearchTravel.class);

        startActivity(i);

    }




}
