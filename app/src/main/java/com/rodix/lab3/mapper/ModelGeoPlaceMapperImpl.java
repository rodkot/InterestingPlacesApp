package com.rodix.lab3.mapper;

import com.rodix.lab3.dto.ModelGeoPlaceDto;
import com.rodix.lab3.model.ModelGeoPlace;

public class ModelGeoPlaceMapperImpl implements DtoModelMapper<ModelGeoPlaceDto,ModelGeoPlace> {
    @Override
    public ModelGeoPlaceDto mapFromModel(ModelGeoPlace place) {
        return new ModelGeoPlaceDto(
                new ModelGeoPlaceDto.PointDto(place.getLat(),place.getLng()),
                place.getCountry(),
                place.getCity(),
                place.getName(),
                place.getState(),
                place.getPostCode());
    }

    @Override
    public ModelGeoPlace mapToModel(ModelGeoPlaceDto modelGeoPlaceDto) {
        return new ModelGeoPlace(
                modelGeoPlaceDto.getCountry(),
                modelGeoPlaceDto.getCity(),
                modelGeoPlaceDto.getName(),
                modelGeoPlaceDto.getState(),
                modelGeoPlaceDto.getPostCode(),
                modelGeoPlaceDto.getPoint().getLat(),
                modelGeoPlaceDto.getPoint().getLng());
    }


}
