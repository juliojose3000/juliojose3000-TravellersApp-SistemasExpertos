package com.example.travellersapp_sistemasexpertos.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.utilities.Dates;
import com.example.travellersapp_sistemasexpertos.utilities.SentMail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReservationDetailsFragment extends Fragment {

    private TextView textViewPaquete;
    private TextView textViewUser;
    private TextView textViewPrice;
    private TextView textViewAirport;
    private TextView textViewHotel;
    private TextView textViewPaymentDate;
    private TextView textViewDateStart;
    private TextView textViewDateEnd;
    private boolean flag;
    private String message;
    private Button buttonSendEmail;
    private Button backButton;
    private Bundle bundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        bundle = getArguments();

        if(bundle != null){

            MainActivity.SAVED_STATE_PAYMENT_DETAILS_FRAGMENT = bundle;

        }else{

            bundle = MainActivity.SAVED_STATE_PAYMENT_DETAILS_FRAGMENT;

        }

        MainActivity.LAST_FRAGMENT = MainActivity.RESERVATION_DETAILS_FRAGMENT;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_made_payment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        flag = false;

        buttonSendEmail = getView().findViewById(R.id.button_send_email);

        int idPackage = bundle.getInt("idTravelPackage");

        TravelPackage travelPackage = Data.getTravelPackageById(idPackage);

        textViewPaquete = getView().findViewById(R.id.textView_paquete);

        textViewPaquete.setText("Paquete reservado: "+travelPackage.getName());

        textViewUser = getView().findViewById(R.id.textView_user);

        textViewUser.setText("Cliente: "+Data.loggedUser.getName()+" "+Data.loggedUser.getLastName());

        textViewPrice = getView().findViewById(R.id.textView_price);

        textViewPrice.setText("Pago: $"+travelPackage.getCostWithFormat());

        textViewAirport = getView().findViewById(R.id.textView_airport);

        textViewAirport.setText("Aeropuerto: "+travelPackage.getAirport().getName());

        textViewHotel = getView().findViewById(R.id.textView_hotel);

        textViewHotel.setText("Hotel: "+travelPackage.getHotel().getName());

        textViewPaymentDate = getView().findViewById(R.id.textView_payment_date);

        Dates dates = new Dates();

        textViewPaymentDate.setText("Fecha de reservación: "+dates.getDateOfToday());

        textViewDateStart = getView().findViewById(R.id.textView_date_start);

        textViewDateStart.setText("Desde el: "+travelPackage.getStartDate().toString());

        textViewDateEnd = getView().findViewById(R.id.textView_date_end);

        textViewDateEnd.setText("Hasta el: "+travelPackage.getEndDate());

        message =
                " - "+textViewPaquete.getText().toString()+"\n"+
                        " - "+textViewUser.getText().toString()+"\n"+
                        " - Costo de la reservación: "+travelPackage.getCostWithFormat()+"\n"+
                        " - "+textViewAirport.getText().toString()+"\n"+
                        " - "+textViewHotel.getText().toString()+"\n"+
                        " - "+textViewPaymentDate.getText().toString()+"\n"+
                        " - "+textViewDateStart.getText().toString()+"\n"+
                        " - "+textViewDateEnd.getText().toString();


        buttonSendEmail = getView().findViewById(R.id.button_send_email);

        buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });


        backButton = getView().findViewById(R.id.button_finish);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }




    public void sendEmail() {

        final User loggedUser = Data.loggedUser;

        Toast.makeText(getActivity(), "Enviando correo...", Toast.LENGTH_LONG).show();

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



    public void finish(){

        getFragmentManager().beginTransaction().remove(this).commit();

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchTravelFragment()).commit();
    }





}
