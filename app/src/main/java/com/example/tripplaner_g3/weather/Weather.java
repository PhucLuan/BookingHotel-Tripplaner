package com.example.tripplaner_g3.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

public class Weather{
    @JsonProperty("id")
    @Expose
    public int id;

    @JsonProperty("main")
    @Expose
    public String main;

    @JsonProperty("description")
    @Expose
    public String description;

    @JsonProperty("icon")
    @Expose
    public String icon;
}

