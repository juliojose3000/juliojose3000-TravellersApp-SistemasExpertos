package com.example.travellersapp_sistemasexpertos.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;

import java.util.ArrayList;
import java.util.List;

public class SearchTravel extends BaseActivity {

    Spinner spinner_categories;

    Spinner spinner_userType;

    Spinner spinner_people;

    EditText editTextMaxprice;

    List<String> arrayListCategories;

    List<String> listUsersTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_travel);

        spinner_categories = findViewById(R.id.spinner_category);

        spinner_userType = findViewById(R.id.spinner_traveller_type);

        spinner_people = findViewById(R.id.spinner_amount_people);

        editTextMaxprice = findViewById(R.id.editText_max_price);

        createCategoriesSpinner();

        createUserTypesSpinner();

    }

    public void exit(View v){

        finish();

    }


    public void searchTravel(View v){

        if(!isThereInternetAccess()){
            Toast.makeText(this,"Compruebe su conexión a internet e intente de nuevo",Toast.LENGTH_SHORT).show();
            return;
        }

        //si la informacion aun no se ha cargado, que espere
        while(!MainActivity.isAllDataLoaded){}

        String amountOfPeople = spinner_people.getSelectedItem().toString();

        String maxPrice = editTextMaxprice.getText().toString();

        String categoryTravel = spinner_categories.getSelectedItem().toString();

        String userType = spinner_userType.getSelectedItem().toString();

        Intent i = new Intent(this, TravellsResults.class);

        i.putExtra("amountOfPeople", amountOfPeople);

        i.putExtra("price", maxPrice);

        i.putExtra("category", categoryTravel);

        i.putExtra("userType", userType);

        startActivity(i);

    }

    private void createCategoriesSpinner(){

        arrayListCategories = new ArrayList();

        arrayListCategories.add("Cualquiera");
        arrayListCategories.add("Playa");
        arrayListCategories.add("Playa y Montana");
        arrayListCategories.add("Montana");
        arrayListCategories.add("Ciudad");
        arrayListCategories.add("Isla");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayListCategories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_categories.setAdapter(adapter);

    }

    private void createUserTypesSpinner(){

        listUsersTypes = new ArrayList<>();

        listUsersTypes.add("Cualquiera");
        listUsersTypes.add("Relajado");
        listUsersTypes.add("Aventurero");
        listUsersTypes.add("Deportista");

        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listUsersTypes);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_userType.setAdapter(adapter2);

    }



}
