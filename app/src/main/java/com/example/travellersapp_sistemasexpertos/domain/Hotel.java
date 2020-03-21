package com.example.travellersapp_sistemasexpertos.domain;

public class Hotel {
    private int idHotel;
    private String name;
    private String email;
    private String phone;
    private String offers;

    public Hotel() {
    }

    public Hotel(int idHotel, String name, String email, String phone, String offers) {
        this.idHotel = idHotel;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.offers = offers;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }
}
