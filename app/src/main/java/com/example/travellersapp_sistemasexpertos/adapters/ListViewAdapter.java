package com.example.travellersapp_sistemasexpertos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.domain.ListViewItem;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems;

    private Context context;

    public ListViewAdapter(ArrayList<ListViewItem> listViewItems, Context context) {
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

        ListViewItem item = (ListViewItem) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.item,null);
        ImageView imgFoto = view.findViewById(R.id.imageView_item);
        TextView textViewTitle = view.findViewById(R.id.textview_item_title);
        TextView textViewPrice = view.findViewById(R.id.textview_item_price);

        imgFoto.setImageResource(item.getImgFoto());
        textViewTitle.setText(item.getTitle());
        textViewPrice.setText(item.getPrice());

        return view;
    }





}
