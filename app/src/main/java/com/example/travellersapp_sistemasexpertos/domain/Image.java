package com.example.travellersapp_sistemasexpertos.domain;

public class Image {

    private int idImage;
    private int idTouristDestination;
    private String url;

    public Image() {
    }

    public Image(int idImage, int idTouristDestination, String url) {
        this.idImage = idImage;
        this.idTouristDestination = idTouristDestination;
        this.url = url;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdTouristDestination() {
        return idTouristDestination;
    }

    public void setIdTouristDestination(int idTouristDestination) {
        this.idTouristDestination = idTouristDestination;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
