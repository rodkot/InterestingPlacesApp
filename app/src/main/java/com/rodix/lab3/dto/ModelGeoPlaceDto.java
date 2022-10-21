package com.rodix.lab3.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class ModelGeoPlaceDto {
    @SerializedName("point")
    private final PointDto point;

    @SerializedName("country")
    private final String country;

    @SerializedName("city")
    private final String city;

    @SerializedName("name")
    private final String name;

    @SerializedName("state")
    private final String state;

    @SerializedName("postcode")
    private final String postCode;


    public String getState() {
        return state;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public PointDto getPoint() {
        return point;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public ModelGeoPlaceDto(PointDto point, String country, String city, String name, String state, String postCode) {
        this.point = point;
        this.country = country;
        this.city = city;
        this.name = name;
        this.state = state;
        this.postCode = postCode;
    }

    public static class PointDto implements Serializable {

        @SerializedName("lat")
        private final double lat;

        @SerializedName("lng")
        private final double lng;

        public PointDto(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }

}
