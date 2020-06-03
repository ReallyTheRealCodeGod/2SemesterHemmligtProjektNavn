package com.automobil.webdemoautomobil.utility;

import com.automobil.webdemoautomobil.models.*;
import com.automobil.webdemoautomobil.repositories.VariablePricesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillSession {
    Autocamper autocamper;
    Rental rental;
    ArrayList<Accessory> accessoryList;
    VariablePrices prices;
    Customer customer;

    public BillSession(){
        autocamper = null;
        rental = null;
        accessoryList = null;
        customer = null;
        prices = null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Autocamper getAutocamper() {
        return autocamper;
    }

    public void setAutocamper(Autocamper autocamper) {
        this.autocamper = autocamper;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public ArrayList<Accessory> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(ArrayList<Accessory> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public VariablePrices getPrices() {
        return prices;
    }
    public void setPrices(VariablePrices prices) {
        this.prices = prices;
    }

    public Bill save(){
        return null;
    }
}
