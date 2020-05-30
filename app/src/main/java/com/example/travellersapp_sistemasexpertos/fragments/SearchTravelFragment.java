package com.example.travellersapp_sistemasexpertos.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.activities.TravellsResults;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchTravelFragment extends Fragment {

    Spinner spinner_categories;

    Spinner spinner_userType;

    Spinner spinner_people;

    Spinner editTextMaxprice;

    List<String> arrayListCategories;

    List<String> listUsersTypes;

    Button searchButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_search_travel, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spinner_categories = getView().findViewById(R.id.spinner_category);

        spinner_userType = getView().findViewById(R.id.spinner_traveller_type);

        spinner_people = getView().findViewById(R.id.spinner_amount_people);

        editTextMaxprice = getView().findViewById(R.id.spinner_price_expected);

        searchButton = getView().findViewById(R.id.button_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTravel();
            }
        });

        createCategoriesSpinner();

        createUserTypesSpinner();

    }




    private void createCategoriesSpinner(){

        arrayListCategories = new ArrayList();

        arrayListCategories.add("Cualquiera");
        arrayListCategories.add("Playa");
        arrayListCategories.add("Playa y Montana");
        arrayListCategories.add("Montana");
        arrayListCategories.add("Ciudad");
        arrayListCategories.add("Isla");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,arrayListCategories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_categories.setAdapter(adapter);

    }

    private void createUserTypesSpinner(){

        listUsersTypes = new ArrayList<>();

        listUsersTypes.add("Cualquiera");
        listUsersTypes.add("Relajado");
        listUsersTypes.add("Aventurero");
        listUsersTypes.add("Deportista");

        ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listUsersTypes);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_userType.setAdapter(adapter2);

    }



    public void searchTravel(){

        //si la informacion aun no se ha cargado, que espere
        while(!MainActivity.isAllDataLoaded){}

        String amountOfPeople = spinner_people.getSelectedItem().toString();

        String maxPrice = editTextMaxprice.getSelectedItem().toString();

        String categoryTravel = spinner_categories.getSelectedItem().toString();

        String userType = spinner_userType.getSelectedItem().toString();

        Intent i = new Intent(getActivity(), TravellsResults.class);

        i.putExtra("amountOfPeople", amountOfPeople);

        i.putExtra("price", maxPrice);

        i.putExtra("category", categoryTravel);

        i.putExtra("userType", userType);

        startActivity(i);

    }








}
