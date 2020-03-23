package com.example.travellersapp_sistemasexpertos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<TravelPackage> listViewItems;

    private Context context;

    public ListViewAdapter(ArrayList<TravelPackage> listViewItems, Context context) {
        this.listViewItems = listViewItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TravelPackage travelPackage = (TravelPackage) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.item,null);
        ImageView imageView = view.findViewById(R.id.imageView_item);
        TextView textViewTitle = view.findViewById(R.id.textview_item_title);
        TextView textViewPrice = view.findViewById(R.id.textview_item_price);

        //imgFoto.setImageResource(item.getImgFoto());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.preview);

        Glide.with(context)
                .load(travelPackage.getImagenURL())
                .apply(requestOptions)
                .into(imageView);


        textViewTitle.setText(travelPackage.getName());
        textViewPrice.setText("Desde: $"+travelPackage.getCost()+"");

        return view;
    }





}
