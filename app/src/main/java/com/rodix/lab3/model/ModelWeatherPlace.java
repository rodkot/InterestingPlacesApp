package com.rodix.lab3.model;

public class ModelWeatherPlace {
    Double temperature;
    Double pressure;
    Double humidity;
    Wind wind;
    Double visibility;
    String description;

    public ModelWeatherPlace(Double temperature, Double pressure, Double humidity, Wind wind, Double visibility, String description) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.visibility = visibility;
        this.description = description;
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

    public Wind getWind() {
        return wind;
    }

    public Double getVisibility() {
        return visibility;
    }

    public String getDescription() {
        return description;
    }

    public static class Wind {
        Double speed;

        public Wind(Double speed) {
            this.speed = speed;
        }

        public Double getSpeed() {
            return speed;
        }
    }
}
