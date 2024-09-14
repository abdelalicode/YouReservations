package com.Youreserve.entity;

public class Hotel {

    private String name;
    private String address;
    private String city;
    private String phone;

    public Hotel(String name, String address, String city, String phone) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return "Hotel { " +
                "name='" + name +
                ", address='" + address +
                ", city='" + city +
                ", phone='" + phone +
                " }";
    }
}
