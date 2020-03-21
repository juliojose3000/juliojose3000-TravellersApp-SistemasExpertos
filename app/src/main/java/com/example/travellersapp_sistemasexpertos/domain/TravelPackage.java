package com.example.travellersapp_sistemasexpertos.domain;

import java.sql.Date;

public class TravelPackage {

    private int idTravelPackage;
    private Date startDate;
    private Date endDate;
    private float cost;
    private String duration;
    private String name;
    private String description;
    private Hotel hotel;
    private Airport airport;
    private String imagenURL;
    private String touristType;
    private String typeOfRoute;

    public TravelPackage() {
        this.hotel=new Hotel();
        this.airport=new Airport();
    }

    public TravelPackage(int idTravelPackage, Date startDate, Date endDate, float cost,
                         String duration, String name, String description, Hotel hotel,
                         Airport airport, String imagenURL, String touristType,
                         String typeOfRoute) {
        this.idTravelPackage = idTravelPackage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.duration = duration;
        this.name = name;
        this.description = description;
        this.hotel = hotel;
        this.airport = airport;
        this.imagenURL = imagenURL;
        this.touristType = touristType;
        this.typeOfRoute = typeOfRoute;
    }

    public int getIdTravelPackage() {
        return idTravelPackage;
    }

    public void setIdTravelPackage(int idTravelPackage) {
        this.idTravelPackage = idTravelPackage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public String getTouristType() {
        return touristType;
    }

    public void setTouristType(String touristType) {
        this.touristType = touristType;
    }

    public String getTypeOfRoute() {
        return typeOfRoute;
    }

    public void setTypeOfRoute(String typeOfRoute) {
        this.typeOfRoute = typeOfRoute;
    }
}
