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
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_travells_results);

        listViewItems = findViewById(R.id.listview_travells);

        Bundle bundle = getIntent().getExtras();

        String amountOfPeople = bundle.getString("amountOfPeople");

        if(!amountOfPeople.equals(CUALQUIERA)){

            amountOfPeopleValue = Double.parseDouble(amountOfPeople);

            A = 1;

        }

        String price = bundle.getString("price");

        if(!price.equals(CUALQUIERA)){

            price = Data.removeAllNonNumbersChaterters(price);

            double priceAux = Double.parseDouble(price);

            priceValue = Data.priceValue(priceAux);

            B = 1;

        }

        String categoryTravel = bundle.getString("category");

        if(!categoryTravel.equals(CUALQUIERA)){

            categoryTravelValue = Data.getCategoryValue(categoryTravel);

            C = 1;

        }

        String userType = bundle.getString("userType");

        if(!userType.equals(CUALQUIERA)){

            userTypeValue = Data.getUserTypeValue(userType);

            D = 1;

        }

        listViewAdapter = new ListViewAdapter(Data.getResults(Data.clonePackageTravelList(), amountOfPeopleValue, A, priceValue, B, categoryTravelValue, C,  userTypeValue, D), TravellsResults.this);

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
