package com.automobil.webdemoautomobil.models;

import java.time.LocalDate;
import java.util.HashMap;

public class MaintenanceReport {
    private int id;
    private int cleaningPrice;
    private int fuelGauge;
    private int mileage;
    private HashMap<String, Integer> partStatus;
    private String maintenanceNote;
    private String cleaningNote;
    private int repairCost;
    private LocalDate Date;
    private int autocamperId;

    public MaintenanceReport(){}

    public String getCleaningNote() {
        return cleaningNote;
    }

    public void setCleaningNote(String cleaningNote) {
        this.cleaningNote = cleaningNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCleaningPrice() {
        return cleaningPrice;
    }

    public void setCleaningPrice(int cleaningPrice) {
        this.cleaningPrice = cleaningPrice;
    }

    public int getFuelGauge() {
        return fuelGauge;
    }

    public void setFuelGauge(int fuelGauge) {
        this.fuelGauge = fuelGauge;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public HashMap<String, Integer> getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(HashMap<String, Integer> partStatus) {
        this.partStatus = partStatus;
    }

    public String getMaintenanceNote() {
        return maintenanceNote;
    }

    public void setMaintenanceNote(String maintenanceNote) {
        this.maintenanceNote = maintenanceNote;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public int getAutocamperId() {
        return autocamperId;
    }

    public void setAutocamperId(int autocamperId) {
        this.autocamperId = autocamperId;
    }
}
