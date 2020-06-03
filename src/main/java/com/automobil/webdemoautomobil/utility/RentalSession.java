package com.automobil.webdemoautomobil.utility;

import com.automobil.webdemoautomobil.models.Accessory;
import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.models.Customer;
import com.automobil.webdemoautomobil.models.Rental;
import com.automobil.webdemoautomobil.repositories.AccessoryRepository;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import com.automobil.webdemoautomobil.repositories.CustomerRepository;
import com.automobil.webdemoautomobil.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RentalSession {
    private LocalDateTime timer;

    CustomerRepository cRep;
    RentalRepository rRep;
    AccessoryRepository accRep;
    AutocamperRepository autoRep;

    private Autocamper autocamper;
    private Customer customer;
    private Rental rental;
    private ArrayList<Accessory> accessories;

    public RentalSession() throws SQLException{
        cRep = new CustomerRepository();
        rRep = new RentalRepository();
        accRep = new AccessoryRepository();
        autoRep = new AutocamperRepository();

        rental = null;
        autocamper = null;
        customer = null;
        accessories = new ArrayList<>();
        timer = LocalDateTime.now();
    }

    public Autocamper getAutocamper() {
        return autocamper;
    }
    public void setAutocamper(Autocamper autocamper) {
        autocamper.setStatus(Autocamper.RESERVED);
        autoRep.update(autocamper);
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

    public ArrayList<Accessory> getAccessories() {
        return accessories;
    }
    public void setAccessories(ArrayList<Accessory> accessories) {
        this.accessories = accessories;
    }

    public void save(){
        rental.setAutocamperId(autocamper.getId());
        rental.setCustomerId(cRep.create(customer).getId());
        rental = rRep.create(rental);
        for(Accessory a : accessories){
            a.setRentalId(rental.getId());
            accRep.update(a);
        }
    }

    @Override
    public String toString(){
        String tostring = "Rental: ";
        if(rental == null){tostring += "null";}else{tostring += rental.toString();}
        tostring += "\nCustomer: ";
        if(customer == null){tostring += "null";}else{tostring += customer.toString();}
        tostring += "\nAutocamper: ";
        if(autocamper == null){tostring += "null";}else{tostring += autocamper.toString();}
        tostring += "\n";
        return tostring;
    }
}
