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

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Estas dos lineas permiten que el codigo de obtener la direccion ipv4 apartir del nombre
        //del host sirva
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new DBHelper();

        setContentView(R.layout.activity_main);
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
