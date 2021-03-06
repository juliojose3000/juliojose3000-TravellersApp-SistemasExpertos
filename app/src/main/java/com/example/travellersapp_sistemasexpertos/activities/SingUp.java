package com.example.travellersapp_sistemasexpertos.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.domain.User;

import androidx.appcompat.app.AppCompatActivity;

public class SingUp extends AppCompatActivity {

    private EditText editTextName;

    private EditText editTextLastname;

    private EditText editTextEmail;

    private EditText editTextPhone;

    private EditText editTextUsername;

    private EditText editTextPassword;

    private EditText editTextPassword2;

    private String whereIGo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sing_up);

        editTextName = findViewById(R.id.editText_name);

        editTextLastname = findViewById(R.id.editText_lastname);

        editTextEmail = findViewById(R.id.editText_email);

        editTextPhone = findViewById(R.id.editText_telephono);

        editTextUsername = findViewById(R.id.editText_username_register);

        editTextPassword = findViewById(R.id.editText_password_register);

        editTextPassword2 = findViewById(R.id.editText_password2_register);


    }


    public void registrarse(View v){

        final String name = editTextName.getText().toString();
        final String lastname = editTextLastname.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String phone = editTextPhone.getText().toString();
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String password2 = editTextPassword2.getText().toString();

        if(!isThereInternetAccess()){
            Toast.makeText(this,"Compruebe su conexión a internet e intente de nuevo",Toast.LENGTH_SHORT).show();
            return;
        }

        if(name.equals("") || lastname.equals("") || email.equals("") ||
                phone.equals("") || username.equals("") || password.equals("")){
            changeColorEmptyEditText(name, lastname, email, phone, username, password);
            Toast.makeText(this,"Complete los campos requeridos",Toast.LENGTH_SHORT).show();
            return;
        }
        changeColorEmptyEditText(name, lastname, email, phone, username, password);

        if(!isValidEmail(email)){
            Toast.makeText(this,"El correo no es válido",Toast.LENGTH_SHORT).show();
            changeBadColorEditText(editTextEmail);
            return;
        }
        changeGoodColorEditText(editTextEmail);

        if(Data.doesThisUsernameExists(username)){
            Toast.makeText(this,"El nombre de usuario ya existe, pruebe con otro",Toast.LENGTH_SHORT).show();
            changeBadColorEditText(editTextUsername);
            return;
        }
        changeGoodColorEditText(editTextUsername);

        if(!password.equals(password2)){
            Toast.makeText(this,"Las contraseñas no coinciden. Intente de nuevo",Toast.LENGTH_SHORT).show();
            changeBadColorEditText(editTextPassword2);
            changeBadColorEditText(editTextPassword);
            return;
        }
        changeGoodColorEditText(editTextPassword2);
        changeGoodColorEditText(editTextPassword);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                    DBHelper.insertUser(name, lastname, email, phone, username, password);

                return null;
            }

        }.execute();

        if(MainActivity.USERS.size()==0){

            Toast.makeText(this,"Espere un momento...",Toast.LENGTH_SHORT).show();

            MainActivity.loadDataFromDB(getApplicationContext());

        }


        User user = new User(Data.getLastIDUser(), username, password, name, lastname, phone, email);

        Data.loggedUser = user;

        finish();

        Toast.makeText(this, "Bienvenido "+Data.loggedUser.getName(), Toast.LENGTH_SHORT).show();


    }

    private void changeColorEmptyEditText(String name, String lastname, String email,
                                          String phone, String username, String password){

        if(name.equals("")){changeBadColorEditText(editTextName);}
        else{changeGoodColorEditText(editTextName);}

        if(lastname.equals("")){changeBadColorEditText(editTextLastname);}
        else{changeGoodColorEditText(editTextLastname);}

        if(email.equals("")){changeBadColorEditText(editTextEmail);}
        else{changeGoodColorEditText(editTextEmail);}

        if(phone.equals("")){changeBadColorEditText(editTextPhone);}
        else{changeGoodColorEditText(editTextPhone);}

        if(username.equals("")){changeBadColorEditText(editTextUsername);}
        else{changeGoodColorEditText(editTextUsername);}

        if(password.equals("")){changeBadColorEditText(editTextPassword);}
        else{changeGoodColorEditText(editTextPassword);}

        if(password.equals("")){changeBadColorEditText(editTextPassword2);}
        else{changeGoodColorEditText(editTextPassword2);}

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public void cancel(View v){

        AlertDialog diaBox = askOption(
                "Salir","¿Está seguro que desea cancelar el registro?",
                "Si","No");

        diaBox.show();

    }


    public void changeBadColorEditText(EditText editText){

        ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
        editText.setBackgroundTintList (colorStateList);

    }

    public void changeGoodColorEditText(EditText editText){

        ColorStateList colorStateList = ColorStateList.valueOf(Color.argb(255, 41, 121, 255));
        editText.setBackgroundTintList (colorStateList);

    }

    public boolean isThereInternetAccess(){

        return true;

    }

    @Override
    public void onBackPressed() {

        finish();

    }



    public AlertDialog askOption(String title, String message, String positive, String negative)
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle(title)
                .setMessage(message)


                .setPositiveButton(positive, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        finish();

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
