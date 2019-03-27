package com.example.fishlocator;

public class Keeper {
    private long latitude;
    private long longitude;
    private String bait;
    private double weight;
    public Keeper(long latitude,long longitude, String bait,double weight){
        this.latitude = latitude;
        this.longitude=longitude;
        this.bait = bait;
        this.weight = weight;
    }
    public long getLatitude(){
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public String getBait() {
        return bait;
    }

    public double getWeight() {
        return weight;
    }
}
