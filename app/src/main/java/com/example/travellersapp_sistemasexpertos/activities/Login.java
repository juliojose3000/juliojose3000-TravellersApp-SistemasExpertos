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


public class Login extends AppCompatActivity {

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




    public void login(){

        username = editText_username.getText().toString();

        password = editText_password.getText().toString();

        if(username.equals("username") && password.equals("password")){

            DBHelper dbHelper = new DBHelper();

        }else{

            Toast.makeText(this, "Usuario inválido",Toast.LENGTH_LONG).show();

        }

    }



}
