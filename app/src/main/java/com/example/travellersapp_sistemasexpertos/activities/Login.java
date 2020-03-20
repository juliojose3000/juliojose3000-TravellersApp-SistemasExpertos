package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;


public class Login extends BaseActivity {

    private EditText editText_username;

    private EditText editText_password;

    private String username;

    private String password;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        editText_password = findViewById(R.id.editText_password);

        editText_username = findViewById(R.id.editText_username);

        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                login();
            }
        });



    }



    public void login(){

        username = editText_username.getText().toString();

        password = editText_password.getText().toString();

        if(username.equals("username") && password.equals("password")){

            DBHelper dbHelper = new DBHelper();

            Intent i = new Intent(this, MainInterface.class);

            finish();

            startActivity(i);

        }else{

            Toast.makeText(this, "Usuario inválido",Toast.LENGTH_LONG).show();

        }

    }


    public void cancel(View v){

        editText_username.setText("");

        editText_password.setText("");

    }



}
