package com.automobil.webdemoautomobil.utility;

import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.models.Customer;
import com.automobil.webdemoautomobil.models.Rental;

import java.time.LocalDateTime;

public class RentalSession {
    private LocalDateTime timer;

    private Autocamper autocamper;
    private Customer customer;
    private Rental rental;

    public RentalSession(){
        timer = LocalDateTime.now();
    }

    public int timeOut(){
        System.out.println(LocalDateTime.now().compareTo(timer));
        return LocalDateTime.now().compareTo(timer);
    }

    public Autocamper getAutocamper() {
        return autocamper;
    }
    public void setAutocamper(Autocamper autocamper) {
        this.autocamper = autocamper;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Rental getRental() {
        return rental;
    }
    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
