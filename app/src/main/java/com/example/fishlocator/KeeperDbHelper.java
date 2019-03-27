package com.example.fishlocator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class KeeperDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Keeper.db";
    private Cursor cursor;

    public KeeperDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        cursor = initQuery();
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(KeeperInformation.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public Cursor initQuery(){
        SQLiteDatabase db = this.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                KeeperInformation.KeeperEntry.BAIT,
                KeeperInformation.KeeperEntry.WEIGHT,
                KeeperInformation.KeeperEntry.LATITUDE,
                KeeperInformation.KeeperEntry.LONGITUDE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = KeeperInformation.KeeperEntry.BAIT + " = ?";
        String[] selectionArgs = { "Spinner" };


        return db.query(
                KeeperInformation.KeeperEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

    }
    public long writeData(Location location,String bait,double weight){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
                    values.put(KeeperInformation.KeeperEntry.LATITUDE, location.getLatitude());
                    values.put(KeeperInformation.KeeperEntry.LONGITUDE, location.getLongitude());
        values.put(KeeperInformation.KeeperEntry.BAIT,bait);
        values.put(KeeperInformation.KeeperEntry.WEIGHT,weight);
        // Insert the new row, returning the primary key value of the new row
        return db.insert(KeeperInformation.KeeperEntry.TABLE_NAME, null, values);
    }
    public List readData(){
        List keepers = new ArrayList<Keeper>();
        while(cursor.moveToNext()) {
            String bait      = cursor.getString(cursor.getColumnIndexOrThrow(KeeperInformation.KeeperEntry.BAIT));
            double weight    = cursor.getLong(cursor.getColumnIndexOrThrow(KeeperInformation.KeeperEntry.WEIGHT));
            double latitude  = cursor.getLong(cursor.getColumnIndexOrThrow(KeeperInformation.KeeperEntry.LATITUDE));
            double longitude = cursor.getLong(cursor.getColumnIndexOrThrow(KeeperInformation.KeeperEntry.LONGITUDE));
            Keeper keeper = new Keeper(latitude,longitude,bait,weight);
            keepers.add(keeper);
        }
        cursor.close();
        return  keepers;
    }
}