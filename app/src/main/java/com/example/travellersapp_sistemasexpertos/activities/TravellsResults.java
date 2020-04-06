package com.example.travellersapp_sistemasexpertos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.adapters.ListViewAdapter;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;

import java.util.Hashtable;

public class TravellsResults extends BaseActivity {

    private ListView listViewItems;

    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_travells_results);

        listViewItems = findViewById(R.id.listview_travells);

        Bundle bundle = getIntent().getExtras();

        String amountOfPeople = bundle.getString("amountOfPeople");

        double amountOfPeopleValue = Double.parseDouble(amountOfPeople.equals("Cantidad de personas")?"2":amountOfPeople);

        double maxPrice = bundle.getFloat("maxPrice");

        double maxPriceValue = Data.maxPriceValue(maxPrice);

        String categoryTravel = bundle.getString("category");

        double categoryTravelValue = Data.getCategoryValue(categoryTravel);

        String userType = bundle.getString("userType");

        double userTypeValue = Data.getUserTypeValue(userType);

        listViewAdapter = new ListViewAdapter(Data.getResults(Data.clonePackageTravelList(), amountOfPeopleValue, maxPriceValue, categoryTravelValue, userTypeValue), TravellsResults.this);

        listViewItems.setAdapter(listViewAdapter);

        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(!isThereInternetAccess()){
                    Toast.makeText(TravellsResults.this,"Compruebe su conexión a internet e intente de nuevo",Toast.LENGTH_SHORT).show();
                    return;
                }

                TravelPackage travelPackage = (TravelPackage) listViewAdapter.getItem(position);

                Intent i = new Intent(TravellsResults.this, PackageChosen.class);

                i.putExtra("travelPackage", travelPackage.getIdTravelPackage());

                startActivity(i);

            }

        });


    }


    public void back(View v){

        finish();

    }




}
