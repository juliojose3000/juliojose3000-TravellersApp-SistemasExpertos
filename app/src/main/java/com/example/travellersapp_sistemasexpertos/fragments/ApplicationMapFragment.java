package com.example.travellersapp_sistemasexpertos.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.activities.Login;
import com.example.travellersapp_sistemasexpertos.activities.SingUp;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ApplicationMapFragment extends Fragment {

    private TextView textViewPantallaInicio;

    private TextView textViewLogin;

    private TextView textViewBuscarPaquetes;

    private TextView textViewResultadoPaquetes;

    private TextView textViewPaqueteSeleccionado;

    private TextView textViewViajeSeleccionado;

    private TextView textViewAboutUs;

    private TextView textViewMUbiacacionDestino;

    private TextView textViewDetallesReservation;

    private TextView textViewSignUp;

    private Button buttonBack;

    private Fragment fragment;

    private int HOME_FRAGMENT = 0;

    private int SEARCH_FRAGMENT = 1;

    private int ABOUT_US_FRAGMENT = 2;

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

                fragment = new WelcomeScreenFragment();

                int itemId = MainActivity.bottonNav.getMenu().getItem(HOME_FRAGMENT).getItemId();

                changeFragment(itemId);

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

                fragment = new SearchPackagesFragment();

                int itemId = MainActivity.bottonNav.getMenu().getItem(SEARCH_FRAGMENT).getItemId();

                changeFragment(itemId);

            }
        });


        textViewAboutUs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                fragment = new AboutUsFragment();

                int itemId = MainActivity.bottonNav.getMenu().getItem(ABOUT_US_FRAGMENT).getItemId();

                changeFragment(itemId);

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


    private void changeFragment(int itemId){

        MainActivity.bottonNav.setSelectedItemId(itemId);

    }





}
