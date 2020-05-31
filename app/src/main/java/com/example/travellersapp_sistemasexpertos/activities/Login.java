package com.example.travellersapp_sistemasexpertos.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.utilities.Data;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    private EditText editText_username;

    private EditText editText_password;

    private String username;

    private String password;

    private Button loginButton;

    private String whereIGo = "";

    private Button buttonCreateNewAccount;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        editText_password = findViewById(R.id.editText_password);

        editText_username = findViewById(R.id.editText_username);

        loginButton = findViewById(R.id.login_button);

        buttonCreateNewAccount = findViewById(R.id.button_create_new_account);

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                login();
            }
        });

        buttonCreateNewAccount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(Login.this, SingUp.class);

                startActivity(i);

                finish();

            }
        });

        backButton = findViewById(R.id.button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        //si la informacion aun no se ha cargado, que espere
        while(!MainActivity.isAllDataLoaded){}

        if(username.equals("") || password.equals("")){
            Toast.makeText(this,"Complete los campos requeridos",Toast.LENGTH_SHORT).show();
            changeColorEmptyEditText(username, password);
            return;
        }
        changeColorEmptyEditText(username, password);

        if(Data.areValidCredentials(username, password)){

            if(whereIGo.equals("searchTravel")){

                Intent i = new Intent(this, SearchTravel.class);

                finish();

                startActivity(i);

            }else {

                finish();

                Toast.makeText(this, "Bienvenido "+Data.loggedUser.getName(), Toast.LENGTH_SHORT).show();

            }



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



}
