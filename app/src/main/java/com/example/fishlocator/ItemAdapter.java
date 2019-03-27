package com.example.fishlocator;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ItemAdapter extends BaseAdapter {
    private ArrayList<Keeper> keepers= new ArrayList<Keeper>();
    private ArrayList<String>  baits=new ArrayList<String>();///={"s","sa"};
    private ArrayList<String>  weights=new ArrayList<String>();//={"1","2"};
    private ArrayList<String>  locations=new ArrayList<String>();//={"1","2"};

    LayoutInflater mInflater;
    public ItemAdapter(Context c, String[] baits, String [] locations, String[] weights){
        Keeper keeper=null;
        for (int i=0;i<baits.length;++i){
            keeper = new Keeper(Long.parseLong(locations[i]),Long.parseLong(locations[i]),baits[i],Double.parseDouble(weights[i]));
            keepers.add(keeper);
        }
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return keepers.size();
    }

    @Override
    public Object getItem(int position) {
        return keepers.get(position).getBait();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.mylist_layout,null);
        TextView priceTextView = (TextView) v.findViewById(R.id.priceTextView);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView descrTextView = (TextView) v.findViewById(R.id.descrTextView);
        Keeper keeper = keepers.get(position);
        String bait = keeper.getBait();
        String weight = String.valueOf(keeper.getWeight());
        String longitude = String.valueOf(keeper.getLongitude());
        nameTextView.setText(bait);
        descrTextView.setText(longitude);
        priceTextView.setText(weight);
        return v;
    }
}
