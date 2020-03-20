package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.travellersapp_sistemasexpertos.R;

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

        editTextUsername = findViewById(R.id.editText_username);

        editTextPassword = findViewById(R.id.editText_password);


    }


    public void registrarse(View v){

        /*String name = editTextName.getText().toString();
        String lastname = editTextLastname.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();*/


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
