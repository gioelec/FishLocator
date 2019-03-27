package com.example.fishlocator;

import android.provider.BaseColumns;

public final class KeeperInformation {
    public static final String SQL_CREATE_ENTRIES =
                    "CREATE TABLE " + KeeperEntry.TABLE_NAME+ " (" +
                    KeeperEntry._ID + " INTEGER PRIMARY KEY," +
                    KeeperEntry.LATITUDE + " TEXT," +
                    KeeperEntry.LONGITUDE + " TEXT,"+
                            KeeperEntry.WEIGHT+ " TEXT," +
                            KeeperEntry.BAIT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + KeeperEntry.TABLE_NAME;
    private KeeperInformation() {};
    public static class KeeperEntry implements BaseColumns {
        public static final String TABLE_NAME = "Keeper";
        public static final String LATITUDE = "Latitude";
        public static final String LONGITUDE = "Longitude";
        public static final String WEIGHT = "Weight";
        public static final String BAIT = "Bait";
    }
}
