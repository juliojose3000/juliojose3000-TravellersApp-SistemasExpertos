package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.R;

public class MadePayment extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_made_payment);
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
