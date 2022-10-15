package com.rodix.lab3.model;


import java.io.Serializable;

public class ModelGeoPlace implements Serializable {

    private final String country;
    private final String city;
    private final String name;
    private final String state;
    private final String postCode;
    private final double lat;
    private final double lng;

    public ModelGeoPlace(String country, String city, String name, String state, String postCode, double lat, double lng) {
        this.country = country;
        this.city = city;
        this.name = name;
        this.state = state;
        this.postCode = postCode;
        this.lat = lat;
        this.lng = lng;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getPostCode() {
        return postCode;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {return lng;}
}
