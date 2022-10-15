package com.rodix.lab3.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelWeatherPlaceDto {
    @SerializedName("main")
    MainDto mainInfo;

    @SerializedName("wind")
    WindDto wind;

    @SerializedName("weather")
    List<WeatherDto> weather;

    @SerializedName("visibility")
    Double visibility;

    public ModelWeatherPlaceDto(MainDto mainInfo, WindDto wind, List<WeatherDto> weather, Double visibility) {
        this.mainInfo = mainInfo;
        this.wind = wind;
        this.weather = weather;
        this.visibility = visibility;
    }

    public Double getVisibility() {
        return visibility;
    }

    public MainDto getMainInfo() {
        return mainInfo;
    }

    public WindDto getWind() {
        return wind;
    }

    public List<WeatherDto> getWeather() {
        return weather;
    }

    public static class MainDto {
        @SerializedName("temp")
        Double temperature;
        @SerializedName("pressure")
        Double pressure;
        @SerializedName("humidity")
        Double humidity;

        public MainDto(Double temperature, Double pressure, Double humidity) {
            this.temperature = temperature;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public Double getTemperature() {
            return temperature;
        }

        public Double getPressure() {
            return pressure;
        }

        public Double getHumidity() {
            return humidity;
        }
    }

    public static class WindDto {
        @SerializedName("speed")
        Double speed;

        public WindDto(Double speed) {
            this.speed = speed;
        }

        public Double getSpeed() {
            return speed;
        }
    }

    public static class WeatherDto {
        @SerializedName("main")
        String name;
        @SerializedName("description")
        String description;

        public WeatherDto(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }
    }
}
