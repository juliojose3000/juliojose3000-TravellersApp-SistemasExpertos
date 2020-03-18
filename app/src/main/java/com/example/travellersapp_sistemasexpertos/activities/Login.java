package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;

public class Login extends Activity {

    private EditText editText_username;

    private EditText editText_password;

    private String username;

    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        editText_password = findViewById(R.id.editText_password);

        editText_username = findViewById(R.id.editText_username);

    }


    public void login(View v){

        username = editText_username.getText().toString();

        password = editText_password.getText().toString();

        if(username.equals("username") && password.equals("password")){

            DBHelper dbHelper = new DBHelper();



        }else{

            Toast.makeText(this, "Usuario inválido",Toast.LENGTH_LONG).show();

        }

    }

}
