package com.example.travellersapp_sistemasexpertos.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.domain.Dates;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;
import com.example.travellersapp_sistemasexpertos.utilities.SentMail;

public class MadePayment extends BaseActivity {

    TextView textViewUser;
    TextView textViewPrice;
    TextView textViewAirport;
    TextView textViewHotel;
    TextView textViewPaymentDate;
    TextView textViewDateStart;
    TextView textViewDateEnd;
    boolean flag;
    String message;
    Button buttonSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_made_payment);

        Bundle bundle = getIntent().getExtras();

        flag = false;

        buttonSendEmail = findViewById(R.id.button_send_email);

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

        Dates dates = new Dates();

        textViewPaymentDate.setText("Fecha de pago: "+dates.getDateOfToday());

        textViewDateStart = findViewById(R.id.textView_date_start);

        textViewDateStart.setText("Desde el: "+travelPackage.getStartDate().toString());

        textViewDateEnd = findViewById(R.id.textView_date_end);

        textViewDateEnd.setText("Hasta el: "+travelPackage.getEndDate());

        message = textViewUser.getText().toString()+"\n"+
                textViewPrice.getText().toString()+"\n"+
                textViewAirport.getText().toString()+"\n"+
                textViewHotel.getText().toString()+"\n"+
                textViewPaymentDate.getText().toString()+"\n"+
                textViewDateStart.getText().toString()+"\n"+
                textViewDateEnd.getText().toString();

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


    public void sendEmail(View v) {

        final User loggedUser = Data.loggedUser;

        Toast.makeText(this, "Enviando correo...", Toast.LENGTH_LONG).show();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String email = "World.travelersB6@gmail.com";
                String pass = "allanjulio";

                SentMail mail = new SentMail(email, pass);

                String[] toArr = {loggedUser.getMail()};
                mail.set_to(toArr);
                mail.set_from(email);
                mail.set_subject("Confirmación de reserva de viaje");
                mail.setBody("Su reservación se ha completado con éxito, disfrute de su experiencia " +
                        "con World Travel"+"\n\n"+
                        "DETALLES DEL PAQUETE: "+"\n"+
                        message);

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
