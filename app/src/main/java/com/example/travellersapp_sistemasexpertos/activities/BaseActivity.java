package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }


    //metodo para mostrar y ocultar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        Intent i = null;

        if (id == R.id.item1) {

            i = new Intent(this, AboutUs.class);

        } else if (id == R.id.item2) {

            Toast.makeText(this, "Salir", Toast.LENGTH_LONG).show();

        }
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }


    public boolean isThereInternetAccess(){

   return true;

    }

    public void changeBadColorEditText(EditText editText){

        ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
        editText.setBackgroundTintList (colorStateList);

    }

    public void changeGoodColorEditText(EditText editText){

        ColorStateList colorStateList = ColorStateList.valueOf(Color.argb(255, 41, 121, 255));
        editText.setBackgroundTintList (colorStateList);

    }


}
