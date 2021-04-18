package com.example.tripplaner_g3.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo{
    public String id;
    @JsonProperty("url")
    public String url;

    public String getUrl() {
        return url;
    }
}
