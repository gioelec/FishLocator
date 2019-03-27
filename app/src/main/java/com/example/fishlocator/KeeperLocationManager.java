package com.example.fishlocator;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.widget.TextView;

public class KeeperLocationManager {
    private static LocationListener listener;
    private Location lastLocation;
    private static boolean newKeeper;
    private KeeperDbHelper dbHelper;
    private TextView locationTextView;
    private TextInputEditText baitTextInput;
    private TextInputEditText weightTextInput;
    private static LocationManager locationManager;
    private Context mContext;

    public KeeperLocationManager(Context mContext,KeeperDbHelper dbHelper, TextInputEditText baitTextInput, TextView locationTextView, TextInputEditText weightTextInput) {
        this.mContext = mContext;
        this.dbHelper = dbHelper;
        this.locationTextView = locationTextView;
        this.weightTextInput = weightTextInput;
        this.baitTextInput = baitTextInput;
        locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lastLocation = location;
                Log.d("msg", "new location sampled");
                if (newKeeper == true)
                    newPosition();
                newKeeper = false;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
               // startActivity(i); TODO
            }
        };
    }
    public void newPosition(){
        dbHelper.writeData(lastLocation,baitTextInput.getText()+"",Double.parseDouble(weightTextInput.getText()+""));
        locationTextView.setText(lastLocation.getLongitude() + " " + lastLocation.getLatitude());
        Log.d("msg","new location "+baitTextInput.getText()+" "+Double.parseDouble(weightTextInput.getText()+""));
    }
    public static void requestLocationUpdates() {
        try {
            locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            Log.d("msg","init gps requests");
        }catch(SecurityException se) {
            se.printStackTrace();
        }
        newKeeper = true;
    }
}
