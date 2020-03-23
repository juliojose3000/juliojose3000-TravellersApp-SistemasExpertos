package com.example.travellersapp_sistemasexpertos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.adapters.ListViewAdapter;
import com.example.travellersapp_sistemasexpertos.database.Data;
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

        String searchText = bundle.getString("search");

        float maxPrice = bundle.getFloat("maxPrice");

        String categoryTravel = bundle.getString("category");

        String userType = bundle.getString("userType");

        listViewAdapter = new ListViewAdapter(Data.getTravells(searchText, maxPrice, categoryTravel, userType), TravellsResults.this);

        listViewItems.setAdapter(listViewAdapter);

        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TravelPackage travelPackage = (TravelPackage) listViewAdapter.getItem(position);

                Intent i = new Intent(TravellsResults.this, TravelChosen.class);

                i.putExtra("travelPackage", travelPackage.getIdTravelPackage());

                startActivity(i);

            }

        });


    }


    public void back(View v){

        finish();

    }




}
