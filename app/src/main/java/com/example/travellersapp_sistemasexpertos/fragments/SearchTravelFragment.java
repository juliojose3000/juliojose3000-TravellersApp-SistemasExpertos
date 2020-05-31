package com.example.travellersapp_sistemasexpertos.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchTravelFragment extends Fragment {

    private Spinner spinner_categories;

    private Spinner spinner_userType;

    private Spinner spinner_people;

    private Spinner spinner_prices;

    private List<String> arrayListCategories;

    private List<String> listUsersTypes;

    private List<String> listPeople;

    private List<String> listAmountOfPeople;

    private Button searchButton;

    private Bundle bundle;

    private ArrayAdapter categoriesAdapter;

    private ArrayAdapter usersAdapter;

    private ArrayAdapter pricesAdapter;

    private ArrayAdapter amountPeopleAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity.LAST_FRAGMENT = MainActivity.SEARCH_FRAGMENT;

        bundle = new Bundle();

        return inflater.inflate(R.layout.activity_search_travel, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spinner_categories = getView().findViewById(R.id.spinner_category);

        spinner_userType = getView().findViewById(R.id.spinner_traveller_type);

        spinner_people = getView().findViewById(R.id.spinner_amount_people);

        spinner_prices = getView().findViewById(R.id.spinner_price_expected);

        searchButton = getView().findViewById(R.id.button_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTravel();
            }
        });

        createCategoriesSpinner();

        createUserTypesSpinner();

        createPricesSpinner();

        createPeopleSpinner();

        if(MainActivity.SAVED_STATE_SEARCH_PACKAGES_FRAGMENT != null){

            bundle = MainActivity.SAVED_STATE_SEARCH_PACKAGES_FRAGMENT;

            if(bundle.getString("category")!=null){
                setValueOnSpinner(spinner_categories, bundle.getString("category"), categoriesAdapter);
            }

            if(bundle.getString("usertype")!=null){
                setValueOnSpinner(spinner_userType, bundle.getString("usertype"), usersAdapter);
            }

            if(bundle.getString("price")!=null){
                setValueOnSpinner(spinner_prices, bundle.getString("price"), pricesAdapter);
            }

            if(bundle.getString("people")!=null){
                setValueOnSpinner(spinner_people, bundle.getString("people"), amountPeopleAdapter);
            }



        }

    }


    private void setValueOnSpinner(Spinner spinner, String value, ArrayAdapter arrayAdapter){

        int spinnerPosition = arrayAdapter.getPosition(value);

        spinner.setSelection(spinnerPosition);

    }


    private void createCategoriesSpinner(){

        arrayListCategories = new ArrayList();

        arrayListCategories.add("Cualquiera");
        arrayListCategories.add("Playa");
        arrayListCategories.add("Playa y Montana");
        arrayListCategories.add("Montana");
        arrayListCategories.add("Ciudad");
        arrayListCategories.add("Isla");

        categoriesAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,arrayListCategories);

        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_categories.setAdapter(categoriesAdapter);

        spinner_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String category = spinner_categories.getSelectedItem().toString();

                bundle.putString("category", category);

                MainActivity.SAVED_STATE_SEARCH_PACKAGES_FRAGMENT = bundle;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void createUserTypesSpinner(){

        listUsersTypes = new ArrayList<>();

        listUsersTypes.add("Cualquiera");
        listUsersTypes.add("Relajado");
        listUsersTypes.add("Aventurero");
        listUsersTypes.add("Deportista");

        usersAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listUsersTypes);

        usersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_userType.setAdapter(usersAdapter);

        spinner_userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String userType = spinner_userType.getSelectedItem().toString();

                bundle.putString("usertype", userType);

                MainActivity.SAVED_STATE_SEARCH_PACKAGES_FRAGMENT = bundle;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void createPricesSpinner(){

        listPeople = new ArrayList<>();

        listPeople.add("Cualquiera");
        listPeople.add("₡ 10.000");
        listPeople.add("₡ 20.000");
        listPeople.add("₡ 50.000");
        listPeople.add("₡ 80.000");
        listPeople.add("₡ 150.000");
        listPeople.add("₡ 200.000 o más");

        pricesAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listPeople);

        pricesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_prices.setAdapter(pricesAdapter);

        spinner_prices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String price = spinner_prices.getSelectedItem().toString();

                bundle.putString("price", price);

                MainActivity.SAVED_STATE_SEARCH_PACKAGES_FRAGMENT = bundle;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void createPeopleSpinner(){

        listPeople = new ArrayList<>();

        listPeople.add("Cualquiera");
        listPeople.add("1");
        listPeople.add("2");
        listPeople.add("3");
        listPeople.add("4");
        listPeople.add("5");

        amountPeopleAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listPeople);

        amountPeopleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_people.setAdapter(amountPeopleAdapter);

        spinner_people.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String price = spinner_people.getSelectedItem().toString();

                bundle.putString("people", price);

                MainActivity.SAVED_STATE_SEARCH_PACKAGES_FRAGMENT = bundle;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void searchTravel(){

        //si la informacion aun no se ha cargado, que espere
        while(!MainActivity.isAllDataLoaded){}

        String amountOfPeople = spinner_people.getSelectedItem().toString();

        String maxPrice = spinner_prices.getSelectedItem().toString();

        String categoryTravel = spinner_categories.getSelectedItem().toString();

        String userType = spinner_userType.getSelectedItem().toString();

        Fragment fragment = new TravellsResultsFragment();

        Bundle bundle = new Bundle();

        bundle.putString("amountOfPeople", amountOfPeople);

        bundle.putString("price", maxPrice);

        bundle.putString("category", categoryTravel);

        bundle.putString("userType", userType);

        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }








}
