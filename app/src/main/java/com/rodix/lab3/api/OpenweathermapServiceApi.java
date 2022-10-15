package com.rodix.lab3.api;

import com.rodix.lab3.dto.ModelWeatherPlaceDto;
import com.rodix.lab3.model.ModelWeatherPlace;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OpenweathermapServiceApi {
    @GET("weather")
    Observable<ModelWeatherPlaceDto> getWeatherPlace(@Query("lat") Double lat, @Query("lon") Double lon, @Query("units") String units, @Query("lang") String lang);
}
