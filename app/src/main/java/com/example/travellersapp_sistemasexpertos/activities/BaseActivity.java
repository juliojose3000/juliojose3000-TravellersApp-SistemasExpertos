package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
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

        if (id == R.id.item1) {

            Intent i = new Intent(this, AboutUs.class);

            startActivity(i);

        } else if (id == R.id.item2) {

            AlertDialog diaBox = confirmMessage();
            diaBox.show();

        }else if (id == R.id.item3) {

            Intent i = new Intent(this, Login.class);

            startActivity(i);

        }


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

    public AlertDialog confirmMessage()
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Salir de la aplicación")
                .setMessage("¿Está seguro que desea salir de la aplicación?")


                .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        dialog.dismiss();

                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(startMain);
                        System.exit(0);

                    }

                })



                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }


}
