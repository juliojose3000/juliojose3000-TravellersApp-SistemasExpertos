package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.travellersapp_sistemasexpertos.R;

public class SearchTravel extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_travel);
    }

    public void exit(View v){

        finish();

    }


    public void searchTravel(View v){

        Intent i = new Intent(this, TravellsResults.class);

        startActivity(i);

    }



}
