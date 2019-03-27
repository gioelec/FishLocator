package com.example.fishlocator;

public class Keeper {
    private double latitude;
    private double longitude;
    private String bait;
    private double weight;
    public Keeper(double latitude,double longitude, String bait,double weight){
        this.latitude = latitude;
        this.longitude=longitude;
        this.bait = bait;
        this.weight = weight;
    }
    public double getLatitude(){
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getBait() {
        return bait;
    }

    public double getWeight() {
        return weight;
    }
}
