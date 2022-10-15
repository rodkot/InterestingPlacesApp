package com.rodix.lab3.mapper;

import com.rodix.lab3.dto.ModelGeoPlaceDto;
import com.rodix.lab3.dto.ModelInterestingPlaceDto;
import com.rodix.lab3.model.ModelInterestingPlace;

public class ModelInterestingPlaceMapper implements DtoModelMapper<ModelInterestingPlaceDto, ModelInterestingPlace> {

    @Override
    public ModelInterestingPlaceDto mapFromModel(ModelInterestingPlace modelInterestingPlace) {
        return new ModelInterestingPlaceDto(
                new ModelInterestingPlaceDto.MainInfoDto(modelInterestingPlace.getXid(), modelInterestingPlace.getName()),
                new ModelInterestingPlaceDto.DescriptionDto(modelInterestingPlace.getDescription().getWikipedia(),
                        new ModelInterestingPlaceDto.DescriptionDto.InfoDto(modelInterestingPlace.getDescription().getDescr()),
                        new ModelInterestingPlaceDto.DescriptionDto.PhotoDto(modelInterestingPlace.getDescription().getPhoto().getSource(),modelInterestingPlace.getDescription().getPhoto().getHeight(),modelInterestingPlace.getDescription().getPhoto().getWidth())));
    }

    @Override
    public ModelInterestingPlace mapToModel(ModelInterestingPlaceDto modelInterestingPlaceDto) {
        return new ModelInterestingPlace(modelInterestingPlaceDto.getMainInfo().getXid(),
                modelInterestingPlaceDto.getMainInfo().getName(),
                new ModelInterestingPlace.Description(modelInterestingPlaceDto.getDescription().getWikipedia(),new ModelInterestingPlace.Description.Photo(modelInterestingPlaceDto.getDescription().getPhotoDto().getSource(),modelInterestingPlaceDto.getDescription().getPhotoDto().getHeight(),modelInterestingPlaceDto.getDescription().getPhotoDto().getWidth()), modelInterestingPlaceDto.getDescription().getInfo().getDescr()));
    }
}
