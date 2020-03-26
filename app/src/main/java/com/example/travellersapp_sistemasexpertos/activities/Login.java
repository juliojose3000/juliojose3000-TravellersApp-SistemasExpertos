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

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.database.Data;
import com.example.travellersapp_sistemasexpertos.domain.User;

import org.json.JSONException;

import java.util.ArrayList;


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

        if(!isThereInternetAccess()){
            Toast.makeText(this,"Compruebe su conexión a internet e intente de nuevo",Toast.LENGTH_SHORT).show();
            return;
        }

        if(MainActivity.USERS.size()==0){

            Toast.makeText(Login.this,"Espere un momento...",Toast.LENGTH_SHORT).show();

            MainActivity.loadDataFromDB(getApplicationContext());

        }

        if(username.equals("") || password.equals("")){
            Toast.makeText(this,"Complete los campos requeridos",Toast.LENGTH_SHORT).show();
            changeColorEmptyEditText(username, password);
            return;
        }
        changeColorEmptyEditText(username, password);

        if(Data.areValidCredentials(username, password)){

            Intent i = new Intent(this, MainInterface.class);

            finish();

            startActivity(i);

        }else{

            Toast.makeText(this, "Usuario inválido",Toast.LENGTH_SHORT).show();

        }

    }


    public void cancel(View v){

        editText_username.setText("");

        editText_password.setText("");

    }

    private void changeColorEmptyEditText(String name, String password){

        if(name.equals("")){changeBadColorEditText(editText_username);}
        else{changeGoodColorEditText(editText_username);}

        if(password.equals("")){changeBadColorEditText(editText_password);}
        else{changeGoodColorEditText(editText_password);}

    }



}
