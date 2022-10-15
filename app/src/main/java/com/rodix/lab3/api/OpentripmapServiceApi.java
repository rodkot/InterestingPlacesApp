package com.rodix.lab3.api;


import com.rodix.lab3.dto.ModelInterestingPlaceDto;
import com.rodix.lab3.model.ModelInterestingPlace;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface OpentripmapServiceApi {
    @GET("{lang}/places/radius")
    Observable<List<ModelInterestingPlaceDto.MainInfoDto>> getInterestingPlace(@Path("lang") String lang, @Query("radius") Double radius, @Query("lon") Double lon, @Query("lat") Double lat, @Query("format") String format, @Query("limit") Integer limit,@Query("src_geom") String src,@Query("kinds") String kinds);

    @GET("{lang}/places/xid/{xid}")
    Observable<ModelInterestingPlaceDto.DescriptionDto> getDescriptionPlace(@Path("lang") String lang, @Path("xid") String xid);
}
