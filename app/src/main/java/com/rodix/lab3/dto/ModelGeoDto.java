package com.rodix.lab3.dto;

import com.google.gson.annotations.SerializedName;
import com.rodix.lab3.dto.ModelGeoPlaceDto;

import java.util.List;

public class ModelGeoDto {
    @SerializedName("hits")
    private List<ModelGeoPlaceDto> placeArray;

    @SerializedName("took")
    private int took;

    public List<ModelGeoPlaceDto> getPlaceArray() {
        return placeArray;
    }

    public int getTook() {
        return took;
    }
}
