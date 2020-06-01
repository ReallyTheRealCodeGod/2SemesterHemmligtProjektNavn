package com.automobil.webdemoautomobil.models;

public class Accessory {
    private int id;
    private int price;
    private String name;
    private String description;
    private int rentalId;
    private int typeId;

    public Accessory(){};
    public Accessory(int price, String name, String description, int rentalId, int typeId) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.rentalId = rentalId;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getRentalId() {
        return rentalId;
    }
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rentalId=" + rentalId +
                ", typeId=" + typeId +
                '}';
    }
}

