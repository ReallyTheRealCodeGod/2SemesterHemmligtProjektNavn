package com.automobil.webdemoautomobil.models;

public class Autocamper {
    public final static int AVAILABLE = 1;
    public final static int RESERVED = 2;
    public final static int UNDER_MAINTENANCE = 3;
    public final static int RENTED = 4;

    private int id;
    private int status;
    private String picture;
    private int mileage;
    private AutocamperType type;

    public Autocamper() {}
    public Autocamper(int status, String picture, int mileage, AutocamperType type) {
        this.status = status;
        this.picture = picture;
        this.mileage = mileage;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public AutocamperType getType() {
        return type;
    }

    public void setType(AutocamperType type) {
        this.type = type;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
