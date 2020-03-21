package com.example.travellersapp_sistemasexpertos.domain;

public class TouristCompany {
    private int idTouristCompany;
    private String name;
    private String mail;
    private String phone;

    public TouristCompany() {
    }

    public TouristCompany(int idTouristCompany, String name, String mail, String phone) {
        this.idTouristCompany = idTouristCompany;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public int getIdTouristCompany() {
        return idTouristCompany;
    }

    public void setIdTouristCompany(int idTouristCompany) {
        this.idTouristCompany = idTouristCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
