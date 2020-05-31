package com.example.travellersapp_sistemasexpertos.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.activities.PackageChosen;
import com.example.travellersapp_sistemasexpertos.adapters.ListViewAdapter;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.utilities.Data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TravellsResultsFragment extends Fragment {

    private ListView listViewItems;

    private Button backButton;

    private ListViewAdapter listViewAdapter;

    private String CUALQUIERA = "Cualquiera";

    private double amountOfPeopleValue = 0;

    private double priceValue = 0;

    private double categoryTravelValue = 0;

    private double userTypeValue = 0;

    private int A = 0;

    private int B = 0;

    private int C = 0;

    private int D = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if(bundle != null){

            MainActivity.SAVED_STATE_PACKAGES_RESULTS_FRAGMENT = bundle;

        }else{

            bundle = MainActivity.SAVED_STATE_PACKAGES_RESULTS_FRAGMENT;

        }


        String amountOfPeople = bundle.getString("amountOfPeople");

        String price = bundle.getString("price");

        String categoryTravel = bundle.getString("category");

        String userType = bundle.getString("userType");

        if(!amountOfPeople.equals(CUALQUIERA)){

            amountOfPeopleValue = Double.parseDouble(amountOfPeople);

            A = 1;

        }

        if(!price.equals(CUALQUIERA)){

            price = Data.removeAllNonNumbersChaterters(price);

            double priceAux = Double.parseDouble(price);

            priceValue = Data.priceValue(priceAux);

            B = 1;

        }

        if(!categoryTravel.equals(CUALQUIERA)){

            categoryTravelValue = Data.getCategoryValue(categoryTravel);

            C = 1;

        }

        if(!userType.equals(CUALQUIERA)){

            userTypeValue = Data.getUserTypeValue(userType);

            D = 1;

        }

        MainActivity.LAST_FRAGMENT = MainActivity.RESULTS_FRAGMENT;


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_travells_results, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listViewItems = getView().findViewById(R.id.listview_travells);

        backButton = getView().findViewById(R.id.button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        listViewAdapter = new ListViewAdapter(Data.getResults(Data.clonePackageTravelList(), amountOfPeopleValue, A, priceValue, B, categoryTravelValue, C,  userTypeValue, D), getActivity());

        listViewItems.setAdapter(listViewAdapter);

        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TravelPackage travelPackage = (TravelPackage) listViewAdapter.getItem(position);

                Fragment fragment = new PackageDetailsFragment();

                Bundle bundle = new Bundle();

                bundle.putInt("travelPackage", travelPackage.getIdTravelPackage());

                fragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            }

        });

    }


    public void back(){

        getFragmentManager().beginTransaction().remove(this).commit();

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchTravelFragment()).commit();

    }


}
