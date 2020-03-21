package com.example.travellersapp_sistemasexpertos.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.travellersapp_sistemasexpertos.R;

import java.util.ArrayList;
import java.util.List;

public class SearchTravel extends BaseActivity {

    Spinner spinner_categories;

    Spinner spinner_userType;

    List<String> arrayListCategories;

    List<String> listUsersTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_travel);

        spinner_categories = findViewById(R.id.spinner_category);

        spinner_userType = findViewById(R.id.spinner_traveller_type);

        createCategoriesSpinner();

        createUserTypesSpinner();

    }

    public void exit(View v){

        finish();

    }


    public void searchTravel(View v){

        Intent i = new Intent(this, TravellsResults.class);

        startActivity(i);

    }

    private void createCategoriesSpinner(){

        arrayListCategories = new ArrayList();

        arrayListCategories.add("Cualquier tipo de viaje");
        arrayListCategories.add("Automóvil");
        arrayListCategories.add("4x4");
        arrayListCategories.add("lancha");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayListCategories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_categories.setAdapter(adapter);

    }

    private void createUserTypesSpinner(){

        listUsersTypes = new ArrayList<>();

        listUsersTypes.add("Cualquier tipo de usuario");
        listUsersTypes.add("Tranquilo");
        listUsersTypes.add("Aventurero");
        listUsersTypes.add("Científico");

        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listUsersTypes);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_userType.setAdapter(adapter2);

    }



}
