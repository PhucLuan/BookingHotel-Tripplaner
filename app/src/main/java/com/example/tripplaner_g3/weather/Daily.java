package com.example.tripplaner_g3.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.util.List;

public class Daily{
    @JsonProperty("dt")
    @Expose
    public int dt;

    @JsonProperty("sunrise")
    @Expose
    public int sunrise;

    @JsonProperty("sunset")
    @Expose
    public int sunset;

    @JsonProperty("moonrise")
    @Expose
    public int moonrise;

    @JsonProperty("moonset")
    @Expose
    public int moonset;

    @JsonProperty("moon_phase")
    @Expose
    public double moon_phase;

    @JsonProperty("temp")
    @Expose
    public Temp temp;

    @JsonProperty("feels_like")
    @Expose
    public FeelsLike feels_like;

    @JsonProperty("pressure")
    @Expose
    public int pressure;

    @JsonProperty("humidity")
    @Expose
    public int humidity;

    @JsonProperty("dew_point")
    @Expose
    public double dew_point;

    @JsonProperty("wind_speed")
    @Expose
    public double wind_speed;

    @JsonProperty("wind_deg")
    @Expose
    public int wind_deg;

    @JsonProperty("wind_gust")
    @Expose
    public double wind_gust;

    @JsonProperty("weather")
    @Expose
    public List<Weather> weather;

    @JsonProperty("clouds")
    @Expose
    public int clouds;

    @JsonProperty("pop")
    @Expose
    public double pop;

    @JsonProperty("rain")
    @Expose
    public double rain;

    @JsonProperty("uvi")
    @Expose
    public double uvi;
}