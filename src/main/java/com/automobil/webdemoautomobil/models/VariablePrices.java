package com.automobil.webdemoautomobil.models;

public class VariablePrices {
    private int variablePricesId;
    private int excessKilometerPrice;
    private int fuelPrice;
    private int dropOffKilometerPrice;
    private int pickUpKilometerPrice;
    private int cleaningMinPrice;
    private int cleaningMaxPrice;
    private Season[] seasons;

    public VariablePrices(){};

    public VariablePrices(int excessKilometerPrice, int fuelPrice, int dropOffKilometerPrice, int pickUpKilometerPrice, int cleaningMinPrice, int cleaningMaxPrice, Season[] seasons) {
        this.excessKilometerPrice = excessKilometerPrice;
        this.fuelPrice = fuelPrice;
        this.dropOffKilometerPrice = dropOffKilometerPrice;
        this.pickUpKilometerPrice = pickUpKilometerPrice;
        this.cleaningMinPrice = cleaningMinPrice;
        this.cleaningMaxPrice = cleaningMaxPrice;
        this.seasons = seasons;
    }

    public int getVariablePricesId() {
        return variablePricesId;
    }

    public void setVariablePricesId(int variablePricesId) {
        this.variablePricesId = variablePricesId;
    }

    public int getExcessKilometerPrice() {
        return excessKilometerPrice;
    }

    public void setExcessKilometerPrice(int excessKilometerPrice) {
        this.excessKilometerPrice = excessKilometerPrice;
    }

    public int getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(int fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public int getDropOffKilometerPrice() {
        return dropOffKilometerPrice;
    }

    public void setDropOffKilometerPrice(int dropOffKilometerPrice) {
        this.dropOffKilometerPrice = dropOffKilometerPrice;
    }

    public int getPickUpKilometerPrice() {
        return pickUpKilometerPrice;
    }

    public void setPickUpKilometerPrice(int pickUpKilometerPrice) {
        this.pickUpKilometerPrice = pickUpKilometerPrice;
    }

    public int getCleaningMinPrice() {
        return cleaningMinPrice;
    }

    public void setCleaningMinPrice(int cleaningMinPrice) {
        this.cleaningMinPrice = cleaningMinPrice;
    }

    public int getCleaningMaxPrice() {
        return cleaningMaxPrice;
    }

    public void setCleaningMaxPrice(int cleaningMaxPrice) {
        this.cleaningMaxPrice = cleaningMaxPrice;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public void setSeasons(Season[] seasons) {
        this.seasons = seasons;
    }
}
