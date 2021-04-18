package com.example.tripplaner_g3.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.util.List;

public class Hotel {
    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_Name() {
        return hotel_Name;
    }

    public void setHotel_Name(String hotel_Name) {
        this.hotel_Name = hotel_Name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public int getHotel_Price() {
        return hotel_Price;
    }

    public void setHotel_Price(int hotel_Price) {
        this.hotel_Price = hotel_Price;
    }

    public String getHotel_Address() {
        return hotel_Address;
    }

    public void setHotel_Address(String hotel_Address) {
        this.hotel_Address = hotel_Address;
    }

    public Hotel(int hotel_id, String hotel_Name, String hotel_Address,int hotel_Price, List<Photo> photos)
    {
        this.hotel_id = hotel_id;
        this.hotel_Address = hotel_Address;
        this.hotel_Name = hotel_Name;
        this.hotel_Price = hotel_Price;
        this.photos = photos;
    }

    @JsonProperty("hotel_id")
    @Expose
    private int hotel_id;

    @JsonProperty("hotel_Name")
    @Expose
    private String hotel_Name;

    @JsonProperty("photos")
    @Expose
    private List<Photo> photos;

    @JsonProperty("hotel_Price")
    @Expose
    private int hotel_Price;

    @JsonProperty("hotel_Address")
    @Expose
    private String hotel_Address;
}
