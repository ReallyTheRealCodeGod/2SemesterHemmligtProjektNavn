package com.automobil.webdemoautomobil.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class VariablePrices {
    private int excessKilometerPrice;
    private int fuelPrice;
    private int dropOffKilometerPrice;
    private int pickUpKilometerPrice;
    private int cleaningMinPrice;
    private int cleaningMaxPrice;
    private ArrayList<Season> seasons;

    public VariablePrices(){};

    public VariablePrices(int excessKilometerPrice, int fuelPrice, int dropOffKilometerPrice, int pickUpKilometerPrice, int cleaningMinPrice, int cleaningMaxPrice, ArrayList<Season> seasons) {
        this.excessKilometerPrice = excessKilometerPrice;
        this.fuelPrice = fuelPrice;
        this.dropOffKilometerPrice = dropOffKilometerPrice;
        this.pickUpKilometerPrice = pickUpKilometerPrice;
        this.cleaningMinPrice = cleaningMinPrice;
        this.cleaningMaxPrice = cleaningMaxPrice;
        this.seasons = seasons;
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

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public Season getCurrentSeason(){
        for(Season s : seasons){
            LocalDate now = LocalDate.now();
            if(s.getStartDate().isBefore(now) && s.getEndDate().isAfter(now)){
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "VariablePrices{" +
                "excessKilometerPrice=" + excessKilometerPrice +
                ", fuelPrice=" + fuelPrice +
                ", dropOffKilometerPrice=" + dropOffKilometerPrice +
                ", pickUpKilometerPrice=" + pickUpKilometerPrice +
                ", cleaningMinPrice=" + cleaningMinPrice +
                ", cleaningMaxPrice=" + cleaningMaxPrice +
                ", seasons=" + seasons +
                '}';
    }
}
