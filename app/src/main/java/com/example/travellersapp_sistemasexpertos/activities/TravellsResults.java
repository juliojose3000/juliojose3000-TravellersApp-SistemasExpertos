package com.example.travellersapp_sistemasexpertos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.adapters.ListViewAdapter;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;

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

        float maxPrice = bundle.getFloat("maxPrice");

        String categoryTravel = bundle.getString("category");

        String userType = bundle.getString("userType");

        listViewAdapter = new ListViewAdapter(Data.getPackages(amountOfPeople, maxPrice, categoryTravel, userType), TravellsResults.this);

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
