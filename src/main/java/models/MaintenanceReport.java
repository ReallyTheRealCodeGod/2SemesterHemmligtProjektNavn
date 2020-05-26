package models;

import java.time.LocalDate;
import java.util.HashMap;

public class MaintenanceReport extends Model{
    private int id;
    private int cleaningPrice;
    private int fuelGauge;
    private int mileage;
    private HashMap<String, Boolean> partStatus;
    private int repairCost;
    private LocalDate Date;
    private int autocamperId;

    public MaintenanceReport(int id, int cleaningPrice, int fuelGauge, int mileage, HashMap<String, Boolean> partStatus, int repairCost, LocalDate date, int autocamperId) {
        this.id = id;
        this.cleaningPrice = cleaningPrice;
        this.fuelGauge = fuelGauge;
        this.mileage = mileage;
        this.partStatus = partStatus;
        this.repairCost = repairCost;
        Date = date;
        this.autocamperId = autocamperId;
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

    public HashMap<String, Boolean> getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(HashMap<String, Boolean> partStatus) {
        this.partStatus = partStatus;
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
