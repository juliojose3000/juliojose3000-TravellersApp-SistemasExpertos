package com.example.travellersapp_sistemasexpertos.domain;

public class Image {

    private int idImage;
    private int idTravelPackage;
    private String url;

    public Image() {
    }

    public Image(int idImage, int idTravelPackage, String url) {
        this.idImage = idImage;
        this.idTravelPackage = idTravelPackage;
        this.url = url;
    }


    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdTravelPackage() {
        return idTravelPackage;
    }

    public void setIdTravelPackage(int idTravelPackage) {
        this.idTravelPackage = idTravelPackage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
