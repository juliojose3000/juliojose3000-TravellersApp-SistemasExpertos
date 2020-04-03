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
    private String videoURL;
    private int numberOfPersons;
    private TouristCompany touristCompany;
    private ArrayList<TouristDestination> touristDestinations;

    public TravelPackage() {
        this.hotel=new Hotel();
        this.airport=new Airport();
        this.touristDestinations=new ArrayList<TouristDestination>();
    }

    public TravelPackage(int idTravelPackage, String startDate, String endDate, float cost,
                         String duration, String name, String description, Hotel hotel,
                         Airport airport, String touristType, String typeOfRoute,int numberOfPersons,
                         String videoURL, ArrayList<TouristDestination> touristDestinations) {

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
        this.videoURL = videoURL;
        this.touristDestinations=touristDestinations;
        this.numberOfPersons=numberOfPersons;
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

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public TouristCompany getTouristCompany() {
        return touristCompany;
    }

    public void setTouristCompany(TouristCompany touristCompany) {
        this.touristCompany = touristCompany;
    }

    public ArrayList<TouristDestination> getTouristDestinations() {
        return touristDestinations;
    }

    public void setTouristDestinations(ArrayList<TouristDestination> touristDestinations) {
        this.touristDestinations = touristDestinations;
    }
}
