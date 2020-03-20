package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.travellersapp_sistemasexpertos.R;

public class TravelChosen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_chosen);
    }


    public void back(View v){

        finish();

    }

    public void makePayment(View v){

        Intent i = new Intent(this, MadePayment.class);

        startActivity(i);

    }


}
