package com.example.fishlocator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button locationButton;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private TextInputEditText baitTextInput;
    private TextInputEditText weightTextInput;
    private KeeperLocationManager keeperLocationManager;
    private KeeperDbHelper dbHelper;
    private ListView myListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.myListView);
        //locationTextView = (TextView) findViewById(R.id.locationTextView);
        locationButton = (Button) findViewById(R.id.locationButton);
        baitTextInput = (TextInputEditText)findViewById(R.id.baitTextInput);
        weightTextInput = (TextInputEditText)findViewById(R.id.weightTextInput);
        dbHelper = new KeeperDbHelper(this);
        ItemAdapter itemAdapter = new ItemAdapter(this);
        keeperLocationManager = new KeeperLocationManager(this,dbHelper,baitTextInput,weightTextInput,itemAdapter);

        configure_button();

        List data = dbHelper.readData();
        Log.d("msg","retrieving data from db");
        if (data.size()==0)
            Log.d("msg","db empty");
        Keeper keeper=null;
        for (int i=0; i<data.size(); i++){
            keeper = (Keeper)data.get(i);
            itemAdapter.addKeeper(keeper);
        }
        myListView.setAdapter(itemAdapter);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }
    void configure_button() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }

            return;
        }
        // this code won'textView execute IF permissions are not allowed, because in the line above there is return statement.
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                KeeperLocationManager.requestLocationUpdates();

            }
        });
    }
}