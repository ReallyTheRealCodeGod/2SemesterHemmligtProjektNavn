package com.automobil.webdemoautomobil.models;

import java.util.ArrayList;

public class AutocamperType {
    private String brand;
    private String model;
    private ArrayList<BuiltInFeature> builtInFeatures;
    private int price;
    private int productionYear;
    private int weight;
    private int fuelCapacity;
    private int horsePower;
    private int maxSpeed;
    private int standingHeight;
    private int area;
    private int height;
    private int length;
    private int width;
    private String description;

    public AutocamperType() {
    }

    public AutocamperType(String brand, String model, ArrayList<BuiltInFeature> builtInFeatures, int price, int productionYear, int weight, int fuelCapacity, int horsePower, int maxSpeed, int standingHeight, int area, int height, int length, int width, String description) {
        this.brand = brand;
        this.model = model;
        this.builtInFeatures = builtInFeatures;
        this.price = price;
        this.productionYear = productionYear;
        this.weight = weight;
        this.fuelCapacity = fuelCapacity;
        this.horsePower = horsePower;
        this.maxSpeed = maxSpeed;
        this.standingHeight = standingHeight;
        this.area = area;
        this.height = height;
        this.length = length;
        this.width = width;
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ArrayList<BuiltInFeature> getBuiltInFeatures() {
        return builtInFeatures;
    }

    public void setBuiltInFeatures(ArrayList<BuiltInFeature> builtInFeatures) {
        this.builtInFeatures = builtInFeatures;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getStandingHeight() {
        return standingHeight;
    }

    public void setStandingHeight(int standingHeight) {
        this.standingHeight = standingHeight;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
