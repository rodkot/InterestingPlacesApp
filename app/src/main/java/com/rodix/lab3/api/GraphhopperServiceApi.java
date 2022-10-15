package com.rodix.lab3.api;



import com.rodix.lab3.dto.ModelGeoDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GraphhopperServiceApi {
    @GET("geocode")
    Observable<ModelGeoDto> getGeoPlace(@Query("q") String q, @Query("locale") String locale, @Query("limit") Integer limit);

}
