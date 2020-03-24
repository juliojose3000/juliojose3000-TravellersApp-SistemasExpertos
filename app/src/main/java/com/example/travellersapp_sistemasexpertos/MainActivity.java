package com.example.travellersapp_sistemasexpertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.activities.BaseActivity;
import com.example.travellersapp_sistemasexpertos.activities.Login;
import com.example.travellersapp_sistemasexpertos.activities.SingUp;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
import com.example.travellersapp_sistemasexpertos.domain.Image;
import com.example.travellersapp_sistemasexpertos.domain.ReservationPackage;
import com.example.travellersapp_sistemasexpertos.domain.TouristCompany;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private Thread thread;

    public static ArrayList<User> USERS;
    public static ArrayList<ReservationPackage> RESERVATIONS;
    public static ArrayList<TravelPackage> TRAVEL_PACKAGES;
    public static ArrayList<Image> IMAGES;
    public static ArrayList<Hotel> HOTELS;
    public static ArrayList<Airport> AIRPORTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        thread = new Thread(){
            public void run(){
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

            }
        };
        thread.start();

    }



    public void login(View v){

        Intent i = new Intent(this, Login.class);

        startActivity(i);

    }


    public void signUp(View v){

        Intent i = new Intent(this, SingUp.class);

        startActivity(i);

    }

}
