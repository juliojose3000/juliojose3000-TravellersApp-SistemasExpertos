package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.database.Data;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;
import com.example.travellersapp_sistemasexpertos.utilities.SentMail;

import java.io.IOException;

public class MadePayment extends BaseActivity {

    TextView textViewUser;
    TextView textViewPrice;
    TextView textViewAirport;
    TextView textViewHotel;
    TextView textViewPaymentDate;
    TextView textViewDateStart;
    TextView textViewDateEnd;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_made_payment);

        Bundle bundle = getIntent().getExtras();

        int idPackage = bundle.getInt("idTravelPackage");
        TravelPackage travelPackage = Data.getTravelPackageById(idPackage);
        flag = false;
        textViewUser = findViewById(R.id.textView_user);

        textViewUser.setText("Cliente: " + Data.loggedUser.getName() + " " + Data.loggedUser.getLastName());

        textViewPrice = findViewById(R.id.textView_price);

        textViewPrice.setText("Pago: $" + travelPackage.getCost());

        textViewAirport = findViewById(R.id.textView_airport);

        textViewAirport.setText("Aeropuerto: " + travelPackage.getAirport().getName());

        textViewHotel = findViewById(R.id.textView_hotel);

        textViewHotel.setText("Hotel: " + travelPackage.getHotel().getName());

        textViewPaymentDate = findViewById(R.id.textView_payment_date);

        textViewPaymentDate.setText("Fecha de pago: ");

        textViewDateStart = findViewById(R.id.textView_date_start);

        textViewDateStart.setText("Desde el: " + travelPackage.getStartDate().toString());

        textViewDateEnd = findViewById(R.id.textView_date_end);

        textViewDateEnd.setText("Hasta el: " + travelPackage.getEndDate().toString());

    }


    public void finish(View v) {

        Intent i = new Intent(this, MainInterface.class);

        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }


    public void sendEmail(View v) {
        final User loggedUser = Data.loggedUser;

        Toast.makeText(this, "Enviando correo...", Toast.LENGTH_LONG).show();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                SentMail mail = new SentMail("ajosue19.as@gmail.com", "batistuta27!");

                String[] toArr = {loggedUser.getMail()};
                mail.set_to(toArr);
                mail.set_from("ajosue19@gmail.com");
                mail.set_subject("Confirmación de reserva de viaje");
                mail.setBody("Su reservación se ha completado con éxito, disfrute de su experiencia " +
                        "con World Travel");

                try {
                    // mail.addAttachment("/sdcard/filelocation");

                    if (mail.send()) {
                        flag = true;
                    }
                } catch (Exception e) {
                    //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                    Log.e("MailApp", "No se pudo enviar el correo", e);
                }

            }

        });

    }
}










