package com.example.travellersapp_sistemasexpertos.domain;

import java.sql.Date;

public class ReservationPackage {
    private int idReservationPackage;
    private User user;
    private TravelPackage travelPackage;
    private Date reservationDate;

    public ReservationPackage() {
        this.user=new User();
        this.travelPackage=new TravelPackage();
    }

    public ReservationPackage(int idReservationPackage, User user, TravelPackage travelPackage, Date reservationDate) {
        this.idReservationPackage = idReservationPackage;
        this.user = user;
        this.travelPackage = travelPackage;
        this.reservationDate = reservationDate;
    }

    public int getIdReservationPackage() {
        return idReservationPackage;
    }

    public void setIdReservationPackage(int idReservationPackage) {
        this.idReservationPackage = idReservationPackage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
