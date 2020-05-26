package models;

import java.util.Arrays;

public class Autocamper {
    private int id;
    private int status;
    private String photo;
    private int mileage;
    private AutocamperType type;

    public Autocamper() {}
    public Autocamper(int id, int status, String photo, int mileage, AutocamperType type) {
        this.id = id;
        this.status = status;
        this.photo = photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
