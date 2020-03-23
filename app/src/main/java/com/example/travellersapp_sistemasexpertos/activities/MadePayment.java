package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.Data;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;

public class MadePayment extends BaseActivity {

    TextView textViewUser;
    TextView textViewPrice;
    TextView textViewAirport;
    TextView textViewHotel;
    TextView textViewPaymentDate;
    TextView textViewDateStart;
    TextView textViewDateEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_made_payment);

        Bundle bundle = getIntent().getExtras();

        int idPackage = bundle.getInt("idTravelPackage");

        TravelPackage travelPackage = Data.getTravelPackageById(idPackage);

        textViewUser = findViewById(R.id.textView_user);

        textViewUser.setText("Cliente: "+Data.loggedUser.getName()+" "+Data.loggedUser.getLastName());

        textViewPrice = findViewById(R.id.textView_price);

        textViewPrice.setText("Pago: $"+travelPackage.getCost());

        textViewAirport = findViewById(R.id.textView_airport);

        textViewAirport.setText("Aeropuerto: "+travelPackage.getAirport().getName());

        textViewHotel = findViewById(R.id.textView_hotel);

        textViewHotel.setText("Hotel: "+travelPackage.getHotel().getName());

        textViewPaymentDate = findViewById(R.id.textView_payment_date);

        textViewPaymentDate.setText("Fecha de pago: ");

        textViewDateStart = findViewById(R.id.textView_date_start);

        textViewDateStart.setText("Desde el: "+travelPackage.getStartDate().toString());

        textViewDateEnd = findViewById(R.id.textView_date_end);

        textViewDateEnd.setText("Hasta el: "+travelPackage.getEndDate().toString());

    }


    public void finish(View v){

        Intent i = new Intent(this, MainInterface.class);

        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }


    public void sendEmail(View v){

        Toast.makeText(this,"Enviando correo...",Toast.LENGTH_LONG).show();

    }


}
