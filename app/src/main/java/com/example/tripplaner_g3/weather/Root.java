package com.example.tripplaner_g3.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.util.List;

public class Root{
    @JsonProperty("lat")
    @Expose
    public double lat;

    @JsonProperty("lon")
    @Expose
    public double lon;

    @JsonProperty("timezone")
    @Expose
    public String timezone;

    @JsonProperty("timezone_offset")
    @Expose
    public int timezone_offset;

    @JsonProperty("current")
    @Expose
    public Current current;

    @JsonProperty("daily")
    @Expose
    public List<Daily> daily;
}
