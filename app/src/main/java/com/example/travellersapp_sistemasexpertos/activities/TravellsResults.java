package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.adapters.ListViewAdapter;
import com.example.travellersapp_sistemasexpertos.domain.ListViewItem;

import java.util.ArrayList;

public class TravellsResults extends BaseActivity {

    private ListView listViewItems;

    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travells_results);

        listViewItems = findViewById(R.id.listview_travells);

        listViewAdapter = new ListViewAdapter(getArrayItems(), TravellsResults.this);

        listViewItems.setAdapter(listViewAdapter);

    }


    private ArrayList<ListViewItem> getArrayItems(){

        ArrayList<ListViewItem> listItems = new ArrayList<>();

        listItems.add(new ListViewItem(R.drawable.manuelantonio, "Manuel Antonio", "desde: $ 5.000"));

        return listItems;

    }


}