package com.example.travellersapp_sistemasexpertos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
import com.example.travellersapp_sistemasexpertos.fragments.AboutUsFragment;
import com.example.travellersapp_sistemasexpertos.fragments.ApplicationMapFragment;
import com.example.travellersapp_sistemasexpertos.fragments.SearchTravelFragment;
import com.example.travellersapp_sistemasexpertos.fragments.WelcomeScreenFragment;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView bottonNav = findViewById(R.id.bottom_navigation);

        bottonNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WelcomeScreenFragment()).commit();
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



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){

                        case R.id.home_item:
                            selectedFragment = new WelcomeScreenFragment();
                            break;
                        case R.id.about_us_menu_item:
                            selectedFragment = new AboutUsFragment();
                            break;
                        case R.id.application_map_menu_item:
                            selectedFragment = new ApplicationMapFragment();
                            break;
                        case R.id.search_item:
                            selectedFragment = new SearchTravelFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }

            };



}
