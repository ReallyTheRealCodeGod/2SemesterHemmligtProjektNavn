package models;

import java.time.LocalDate;

public class Rental extends Model{
    private int id;
    private int accumulatedPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pickUpLoc;
    private String dropOffLoc;
    private int autocamperId;
    private int maintenanceId;
    private int customerId;

    public Rental(int id, int accumulatedPrice, LocalDate startDate, LocalDate endDate, String pickUpLoc, String dropOffLoc, int autocamperId, int maintenanceId, int customerId) {
        this.id = id;
        this.accumulatedPrice = accumulatedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickUpLoc = pickUpLoc;
        this.dropOffLoc = dropOffLoc;
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

    public String getPickUpLoc() {
        return pickUpLoc;
    }

    public void setPickUpLoc(String pickUpLoc) {
        this.pickUpLoc = pickUpLoc;
    }

    public String getDropOffLoc() {
        return dropOffLoc;
    }

    public void setDropOffLoc(String dropOffLoc) {
        this.dropOffLoc = dropOffLoc;
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
}
