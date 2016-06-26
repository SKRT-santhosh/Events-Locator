package com.example.user.eventsapp;

import java.io.Serializable;

/**
 * Created by User on 22-02-2016.
 */
public class Event implements Serializable{

    public String getEventName() {
        return eventName;
    }

    private String eventName,category;
    private double latitudes, longtitudes;


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

  /*  public com.example.user.eventapp.Event(String name){


        this.eventName = name;
    }*/

    public void setEventName(String name){
        eventName = name;
    }

    public double getLatitudes() {
        return latitudes;
    }

    public void setLatitudes(double lat){latitudes=lat; }

    public double getLongtitudes() {
        return longtitudes;
    }

    public void setLongtitudes(double lon){longtitudes=lon;}

}

