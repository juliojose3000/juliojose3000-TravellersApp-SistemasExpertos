package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.database.Data;
import com.example.travellersapp_sistemasexpertos.domain.User;

public class SingUp extends BaseActivity {

    private EditText editTextName;

    private EditText editTextLastname;

    private EditText editTextEmail;

    private EditText editTextPhone;

    private EditText editTextUsername;

    private EditText editTextPassword;

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


    }


    public void registrarse(View v){

        final String name = editTextName.getText().toString();
        final String lastname = editTextLastname.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String phone = editTextPhone.getText().toString();
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        if(name.equals("") || lastname.equals("") || email.equals("") ||
                phone.equals("") || username.equals("") || password.equals("")){
            Toast.makeText(this,"Complete los campos requeridos",Toast.LENGTH_SHORT).show();
            return;
        }

        if(Data.doesThisUsernameExists(username)){
            Toast.makeText(this,"El nombre de usuario ya existe, pruebe con otro",Toast.LENGTH_SHORT).show();
            return;
        }


        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                    DBHelper.insertUser(name, lastname, email, phone, username, password);

                return null;
            }

        }.execute();

        User user = new User(Data.getLastIDUser(), username, password, name, lastname, phone, email);

        Data.loggedUser = user;

        Intent i = new Intent(this, MainInterface.class);

        startActivity(i);


    }


    public void cancel(View v){

        editTextName.setText("");

        editTextLastname.setText("");

        editTextEmail.setText("");

        editTextPhone.setText("");

        editTextUsername.setText("");

        editTextPassword.setText("");
    }


}
