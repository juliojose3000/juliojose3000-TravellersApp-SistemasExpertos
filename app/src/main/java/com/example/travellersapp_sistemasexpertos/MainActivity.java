package com.example.travellersapp_sistemasexpertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.activities.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Estas dos lineas permiten que el codigo de obtener la direccion ipv4 apartir del nombre
        //del host sirva
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
    }

    //metodo para mostrar y ocultar el menu
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent i = null;
        if (id == R.id.item1) {
            Toast.makeText(this, "Item 1", Toast.LENGTH_LONG).show();
        } else if (id == R.id.item2) {
            Toast.makeText(this, "Item 2", Toast.LENGTH_LONG).show();
        }

        //startActivity(i);
        return super.onOptionsItemSelected(item);
    }


    public void login(View v){

        Intent i = new Intent(this, Login.class);

        startActivity(i);

    }

}
