package models;

import java.time.LocalDate;

public class Season {
    private int surchargePercentage;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public Season(){}
    public Season(int surchargePercentage, String name, LocalDate startDate, LocalDate endDate) {
        this.surchargePercentage = surchargePercentage;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getSurchargePercentage() {
        return surchargePercentage;
    }

    public void setSurchargePercentage(int surchargePercentage) {
        this.surchargePercentage = surchargePercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
