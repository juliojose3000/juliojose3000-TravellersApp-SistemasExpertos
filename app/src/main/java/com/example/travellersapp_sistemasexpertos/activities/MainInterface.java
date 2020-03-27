package com.example.travellersapp_sistemasexpertos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;

import java.util.ArrayList;

public class MainInterface extends BaseActivity {

    private ArrayList<TravelPackage> offersList;

    private HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_interface);

        Toast.makeText(getApplicationContext(), "Bienvenido "+ Data.loggedUser.getName()+" "+
                Data.loggedUser.getLastName(), Toast.LENGTH_LONG).show();


        LinearLayout gallery = findViewById(R.id.linear_layout_offers_galery);

        horizontalScrollView = findViewById(R.id.horizontal_scroll_view_offers);

        LayoutInflater inflater = LayoutInflater.from(this);

        View viewImages;

        offersList = Data.generateOffers();

        RequestOptions requestOptions = new RequestOptions();

        requestOptions.placeholder(R.drawable.preview);

        TextView textViewTitle;

        TextView textViewPrice;

        ImageView imageView;

        for(int i=0; i<offersList.size(); i++){

            viewImages = inflater.inflate(R.layout.offer, gallery, false);

            imageView =  viewImages.findViewById(R.id.imageView_offers);

            final TravelPackage travelPackage = offersList.get(i);

            textViewTitle = viewImages.findViewById(R.id.textView_travel_offer);

            textViewPrice = viewImages.findViewById(R.id.textView_price_offer);

            Glide.with(MainInterface.this)
                    .load(travelPackage.getListImages().get(0).getUrl())
                    .apply(requestOptions)
                    .into(imageView);

            textViewTitle.setText(travelPackage.getName());

            textViewPrice.setText("Desde: $"+travelPackage.getCost());

            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    if(!isThereInternetAccess()){
                        Toast.makeText(MainInterface.this,"Compruebe su conexión a internet e intente de nuevo",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int idTravelPackage = travelPackage.getIdTravelPackage();

                    Intent i = new Intent(MainInterface.this, TravelChosen.class);

                    i.putExtra("travelPackage", idTravelPackage);

                    startActivity(i);

                }
            });

            gallery.addView(viewImages);

        }

        horizontalScrollView.postDelayed(new Runnable() {

            @Override
            public void run() {
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_LEFT);
            }
        }, 10);



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

