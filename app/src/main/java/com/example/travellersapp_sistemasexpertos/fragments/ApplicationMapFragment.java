package com.example.travellersapp_sistemasexpertos.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.activities.AboutUs;
import com.example.travellersapp_sistemasexpertos.activities.AppMapActivity;
import com.example.travellersapp_sistemasexpertos.activities.Login;
import com.example.travellersapp_sistemasexpertos.activities.SearchTravel;
import com.example.travellersapp_sistemasexpertos.activities.SingUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ApplicationMapFragment extends Fragment {

    TextView textViewPantallaInicio;

    TextView textViewLogin;

    TextView textViewBuscarPaquetes;

    TextView textViewResultadoPaquetes;

    TextView textViewPaqueteSeleccionado;

    TextView textViewViajeSeleccionado;

    TextView textViewAboutUs;

    TextView textViewMUbiacacionDestino;

    TextView textViewDetallesReservation;

    TextView textViewSignUp;

    Button buttonBack;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_app_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textViewPantallaInicio = getView().findViewById(R.id.textView_pantalla_inicio);

        textViewLogin = getView().findViewById(R.id.textView_login);

        textViewBuscarPaquetes = getView().findViewById(R.id.textView_buscar_paquetes);

        textViewAboutUs = getView().findViewById(R.id.textView_about_us);

        textViewSignUp = getView().findViewById(R.id.textView_sign_up);


        textViewPantallaInicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Login.class);
                startActivity(i);
            }
        });


        textViewBuscarPaquetes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SearchTravel.class);
                startActivity(i);
            }
        });


        textViewAboutUs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AboutUs.class);
                startActivity(i);
            }
        });



        textViewSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SingUp.class);
                startActivity(i);
            }
        });


    }
}
