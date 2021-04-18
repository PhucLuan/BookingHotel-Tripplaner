package com.example.tripplaner_g3.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.util.List;

public class Current{
    @JsonProperty("dt")
    @Expose
    public int dt;

    @JsonProperty("sunrise")
    @Expose
    public int sunrise;

    @JsonProperty("sunset")
    @Expose
    public int sunset;

    @JsonProperty("temp")
    @Expose
    public double temp;

    @JsonProperty("feels_like")
    @Expose
    public double feels_like;

    @JsonProperty("pressure")
    @Expose
    public int pressure;

    @JsonProperty("humidity")
    @Expose
    public int humidity;

    @JsonProperty("dew_point")
    @Expose
    public double dew_point;

    @JsonProperty("uvi")
    @Expose
    public double uvi;

    @JsonProperty("clouds")
    @Expose
    public int clouds;

    @JsonProperty("visibility")
    @Expose
    public int visibility;

    @JsonProperty("wind_speed")
    @Expose
    public double wind_speed;

    @JsonProperty("wind_deg")
    @Expose
    public int wind_deg;

    @JsonProperty("weather")
    @Expose
    public List<Weather> weather;
}
