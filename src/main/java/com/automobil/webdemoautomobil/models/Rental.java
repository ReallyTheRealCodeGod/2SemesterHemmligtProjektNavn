package com.automobil.webdemoautomobil.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Rental {
    private int id;
    private int accumulatedPrice;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDate startDate;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDate endDate;
    private long longPickUpLoc;
    private long latPickUpLoc;
    private long longDropOffLoc;
    private long latDropOffLoc;
    private int autocamperId;
    private int maintenanceId;
    private int customerId;

    public Rental() {
    }

    public Rental(int id, int accumulatedPrice, LocalDate startDate, LocalDate endDate, long longPickUpLoc, long latPickUpLoc, long longDropOffLoc, long latDropOffLoc, int autocamperId, int maintenanceId, int customerId) {
        this.accumulatedPrice = accumulatedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.longPickUpLoc = longPickUpLoc;
        this.latPickUpLoc = latPickUpLoc;
        this.longDropOffLoc = longDropOffLoc;
        this.latDropOffLoc = latDropOffLoc;
        this.autocamperId = autocamperId;
        this.maintenanceId = maintenanceId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccumulatedPrice() {
        return accumulatedPrice;
    }

    public void setAccumulatedPrice(int accumulatedPrice) {
        this.accumulatedPrice = accumulatedPrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getLongPickUpLoc() {
        return longPickUpLoc;
    }

    public void setLongPickUpLoc(long longPickUpLoc) {
        this.longPickUpLoc = longPickUpLoc;
    }

    public long getLatPickUpLoc() {
        return latPickUpLoc;
    }

    public void setLatPickUpLoc(long latPickUpLoc) {
        this.latPickUpLoc = latPickUpLoc;
    }

    public long getLongDropOffLoc() {
        return longDropOffLoc;
    }

    public void setLongDropOffLoc(long longDropOffLoc) {
        this.longDropOffLoc = longDropOffLoc;
    }

    public long getLatDropOffLoc() {
        return latDropOffLoc;
    }

    public void setLatDropOffLoc(long latDropOffLoc) {
        this.latDropOffLoc = latDropOffLoc;
    }

    public int getAutocamperId() {
        return autocamperId;
    }

    public void setAutocamperId(int autocamperId) {
        this.autocamperId = autocamperId;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", accumulatedPrice=" + accumulatedPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", longPickUpLoc=" + longPickUpLoc +
                ", latPickUpLoc=" + latPickUpLoc +
                ", longDropOffLoc=" + longDropOffLoc +
                ", latDropOffLoc=" + latDropOffLoc +
                ", autocamperId=" + autocamperId +
                ", maintenanceId=" + maintenanceId +
                ", customerId=" + customerId +
                '}';
    }
}