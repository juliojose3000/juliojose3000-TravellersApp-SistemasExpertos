package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;

public class AppMapActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_map);

        buttonBack = findViewById(R.id.button_back);

        textViewPantallaInicio = findViewById(R.id.textView_pantalla_inicio);

        textViewLogin = findViewById(R.id.textView_login);

        textViewBuscarPaquetes = findViewById(R.id.textView_buscar_paquetes);

        textViewAboutUs = findViewById(R.id.textView_about_us);

        textViewSignUp = findViewById(R.id.textView_sign_up);

        buttonBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textViewPantallaInicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppMapActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppMapActivity.this, Login.class);
                startActivity(i);
            }
        });


        textViewBuscarPaquetes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppMapActivity.this, SearchTravel.class);
                startActivity(i);
            }
        });


        textViewAboutUs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppMapActivity.this, AboutUs.class);
                startActivity(i);
            }
        });



        textViewSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppMapActivity.this, SingUp.class);
                startActivity(i);
            }
        });



    }
}
