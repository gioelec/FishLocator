package com.example.fishlocator;

import android.app.LauncherActivity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ItemAdapter extends BaseAdapter {
    private ArrayList<Keeper> keepers= new ArrayList<Keeper>();
    LayoutInflater mInflater;
    public ItemAdapter(Context c){
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void addKeeper(Keeper keeper){
        keepers.add(keeper);
        Log.d("msg", "adding keeper bait:"+keeper.getBait()+" weight "+keeper.getWeight()
        );
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
        TextView descrTextView = (TextView) v.findViewById(R.id.latitudeTextView);
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
