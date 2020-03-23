package com.example.travellersapp_sistemasexpertos.domain;

import java.util.ArrayList;
import java.util.Date;

public class TravelPackage {

    private int idTravelPackage;
    private String startDate;
    private String endDate;
    private float cost;
    private String duration;
    private String name;
    private String description;
    private Hotel hotel;
    private Airport airport;
    private String touristType;
    private String typeOfRoute;
    private ArrayList<Image> listImages;
    private String videoURL;

    public TravelPackage() {
        this.hotel=new Hotel();
        this.airport=new Airport();
    }

    public TravelPackage(int idTravelPackage, String startDate, String endDate, float cost, String duration, String name, String description, Hotel hotel, Airport airport, String touristType, String typeOfRoute, ArrayList<Image> listImages, String videoURL) {
        this.idTravelPackage = idTravelPackage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.duration = duration;
        this.name = name;
        this.description = description;
        this.hotel = hotel;
        this.airport = airport;
        this.touristType = touristType;
        this.typeOfRoute = typeOfRoute;
        this.listImages = listImages;
        this.videoURL = videoURL;
    }


    public String getVideoURL() {
        return videoURL;
    }

    public int getIdTravelPackage() {
        return idTravelPackage;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public float getCost() {
        return cost;
    }

    public String getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Airport getAirport() {
        return airport;
    }

    public String getTouristType() {
        return touristType;
    }

    public String getTypeOfRoute() {
        return typeOfRoute;
    }

    public ArrayList<Image> getListImages() {
        return listImages;
    }
}
