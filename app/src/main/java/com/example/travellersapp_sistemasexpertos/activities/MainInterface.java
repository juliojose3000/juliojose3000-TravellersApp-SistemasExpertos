package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.domain.User;

import java.util.ArrayList;

public class MainInterface extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

    }


    public void exit(View v){

        Intent i = new Intent(this, MainActivity.class);

        finish();

        startActivity(i);

    }


    public void searchTravel(View v){

        Intent i = new Intent(this, SearchTravel.class);

        startActivity(i);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        exit(null);

    }


}

