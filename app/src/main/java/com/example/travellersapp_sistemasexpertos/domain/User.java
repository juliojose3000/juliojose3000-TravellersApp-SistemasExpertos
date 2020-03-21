package com.example.travellersapp_sistemasexpertos.domain;

public class User {

    private int id;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String phone;
    private String mail;

    public User() {
    }

    public User(int id, String username, String password, String name, String lastName, String phone, String mail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName=lastName;
        this.mail=mail;
        this.phone=phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
