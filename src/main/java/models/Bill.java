package models;

import java.time.LocalDate;

public class Bill extends Model{
    private int id;
    private LocalDate billingDate;
    private String customerFirstName;
    private String customerLastName;
    private int postalCode;
    private String streetName;
    private String streetNr;
    private String apartmentFloor;
    private int accessoryCost;
    private int rentalCost;
    private int totalPrice;

    public Bill(int id, LocalDate billingDate, String customerFirstName, String customerLastName, int postalCode, String streetName, String streetNr, String apartmentFloor, int accessoryCost, int rentalCost, int totalPrice) {
        this.id = id;
        this.billingDate = billingDate;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNr = streetNr;
        this.apartmentFloor = apartmentFloor;
        this.accessoryCost = accessoryCost;
        this.rentalCost = rentalCost;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getApartmentFloor() {
        return apartmentFloor;
    }

    public void setApartmentFloor(String apartmentFloor) {
        this.apartmentFloor = apartmentFloor;
    }

    public int getAccessoryCost() {
        return accessoryCost;
    }

    public void setAccessoryCost(int accessoryCost) {
        this.accessoryCost = accessoryCost;
    }

    public int getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(int rentalCost) {
        this.rentalCost = rentalCost;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
