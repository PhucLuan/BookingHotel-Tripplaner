package com.example.tripplaner_g3.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("id")
    @Expose
    private int id;

    @JsonProperty("name")
    @Expose
    private String name;

    @JsonProperty("url")
    @Expose
    private String url;

    public City(int id, String name, String url){
        this.setId(id);
        this.setName(name);
        this.setUrl(url);
    }

}

