package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.travellersapp_sistemasexpertos.R;
import com.squareup.picasso.Picasso;

public class TravelChosen extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_chosen);

        imageView = findViewById(R.id.imageView_selected_travel);

        /*Picasso.with(TravelChosen.this).load("https://www.larepublica.net/storage/images/2019/03/13/20190313144831.manu.jpg")
                .into(imageView);*/

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.preview);

        Glide.with(TravelChosen.this)
                .load("https://www.larepublica.net/storage/images/2019/03/13/20190313144831.manu.jpg")
                .apply(requestOptions)
                .into(imageView);

    }


    public void back(View v){

        finish();

    }

    public void makePayment(View v){

        Intent i = new Intent(this, MadePayment.class);

        startActivity(i);

    }


}
