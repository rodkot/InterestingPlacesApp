package com.rodix.lab3;

import java.util.Observable;

import retrofit2.http.GET;

public interface GraphhopperServiceApi {
    @GET("geocode")
    Observable<ModelGeoPlace> getGeoPlace();

}
