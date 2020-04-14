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

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.utilities.Data;

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

        }else if (id == R.id.item4) {

            Intent i = new Intent(this, MainActivity.class);//me dirijo a la interfaz de inicio

            Data.loggedUser = null;//cierro la sesion

            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem logoutItem = menu.findItem(R.id.item4);
        MenuItem loginItem = menu.findItem(R.id.item3);

        if(Data.loggedUser==null){
            logoutItem.setVisible(false);
            loginItem.setVisible(true);
        }else{
            logoutItem.setVisible(true);
            loginItem.setVisible(false);
        }



        return true;
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


    public AlertDialog askOption(String title, String message, String positive, String negative, final String function,final Context context)
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle(title)
                .setMessage(message)


                .setPositiveButton(positive, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {


                        switch (function){
                            case "makePayment":
                                ((PackageChosen)context).makePayment();
                                break;
                            case "inisiarSesion":
                                Intent i = new Intent(context, Login.class);
                                i.putExtra("whereIGo","makePayment");
                                startActivity(i);
                                break;
                        }

                        dialog.dismiss();
                    }

                })

                .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }



}
