package com.rodix.lab3.mapper;

import com.rodix.lab3.dto.ModelWeatherPlaceDto;
import com.rodix.lab3.model.ModelWeatherPlace;

public class ModelWeatherPlaceMapperImpl implements DtoModelMapper<ModelWeatherPlaceDto, ModelWeatherPlace> {
    @Override
    public ModelWeatherPlaceDto mapFromModel(ModelWeatherPlace weatherPlace) {
        return new ModelWeatherPlaceDto(
                new ModelWeatherPlaceDto.MainDto(weatherPlace.getTemperature(),
                        weatherPlace.getPressure(),
                        weatherPlace.getHumidity()),
                new ModelWeatherPlaceDto.WindDto(weatherPlace.getWind().getSpeed()),
                null,
                weatherPlace.getVisibility()
        );
    }

    @Override
    public ModelWeatherPlace mapToModel(ModelWeatherPlaceDto modelWeatherPlaceDto) {
        return new ModelWeatherPlace(
                modelWeatherPlaceDto.getMainInfo().getTemperature(),
                modelWeatherPlaceDto.getMainInfo().getPressure(),
                modelWeatherPlaceDto.getMainInfo().getHumidity(),
                new ModelWeatherPlace.Wind(modelWeatherPlaceDto.getWind().getSpeed()),
                modelWeatherPlaceDto.getVisibility(), modelWeatherPlaceDto.getWeather().get(0).getDescription());
    }
}
