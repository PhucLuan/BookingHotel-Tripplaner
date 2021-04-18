package com.example.tripplaner_g3.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

public class FeelsLike{
    @JsonProperty("day")
    @Expose
    public double day;

    @JsonProperty("night")
    @Expose
    public double night;

    @JsonProperty("eve")
    @Expose
    public double eve;

    @JsonProperty("morn")
    @Expose
    public double morn;
}
