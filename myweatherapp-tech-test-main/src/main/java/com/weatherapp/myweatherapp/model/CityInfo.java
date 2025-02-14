package com.weatherapp.myweatherapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityInfo {

  @JsonProperty("address")
  private String address;

  @JsonProperty("description")
  private String description;

  @JsonProperty("currentConditions")
  private CurrentConditions currentConditions;

  @JsonProperty("days")
  private List<Days> days;

  public CurrentConditions getCurrentConditions() {
      return currentConditions;
  }

  public String getAddress() {
      return address;
  }

  public String getDescription() {
      return description;
  }

  public List<Days> getDays() {
      return days;
  }

  public static class CurrentConditions {
    @JsonProperty("temp")
    private String currentTemperature;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("feelslike")
    private String feelslike;

    @JsonProperty("humidity")
    private String humidity;

    @JsonProperty("conditions")
    private String conditions;

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public String getFeelsLike() {
        return feelslike;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getConditions() {
        return conditions;
    }
  }

  public static class Days {
    @JsonProperty("datetime")
    private String date;

    @JsonProperty("temp")
    private String currentTemperature;

    @JsonProperty("tempmax")
    private String maxTemperature;

    @JsonProperty("tempmin")
    private String minTemperature;

    @JsonProperty("conditions")
    private String conditions;

    @JsonProperty("description")
    private String description;

    public String getDate() {
        return date;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getConditions() {
        return conditions;
    }

    public String getDescription() {
        return description;
    }
  }
}
