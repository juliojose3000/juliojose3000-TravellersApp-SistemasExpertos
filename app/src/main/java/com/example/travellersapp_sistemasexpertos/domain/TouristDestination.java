package com.example.travellersapp_sistemasexpertos.domain;

import java.util.ArrayList;

public class TouristDestination {
    private int idTouristDestination;
    private int idTravelPackage;
    private String address;
    private String name;
    private ArrayList<Image> listImages;

    public TouristDestination() {
        this.listImages=new ArrayList<Image>();
    }

    public TouristDestination(int idTouristDestination, String address, String name,ArrayList<Image> listImages
    , int idTravelPackage) {
        this.idTouristDestination = idTouristDestination;
        this.address = address;
        this.name = name;
        this.listImages=listImages;
        this.idTravelPackage=idTravelPackage;
    }

    public int getIdTouristDestination() {
        return idTouristDestination;
    }

    public void setIdTouristDestination(int idTouristDestination) {
        this.idTouristDestination = idTouristDestination;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<Image> getListImages() {
        return listImages;
    }

    public void setListImages(ArrayList<Image> listImages) {
        this.listImages = listImages;
    }

    public int getIdTravelPackage() {
        return idTravelPackage;
    }

    public void setIdTravelPackage(int idTravelPackage) {
        this.idTravelPackage = idTravelPackage;
    }
}
