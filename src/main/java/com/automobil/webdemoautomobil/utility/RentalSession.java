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

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RentalSession {
    private LocalDateTime timer;

    private Autocamper autocamper;
    private Customer customer;
    private Rental rental;
    private ArrayList<Accessory> accessories;

    @Autowired
    private AutocamperRepository aRep;
    @Autowired
    private CustomerRepository cRep;
    @Autowired
    private RentalRepository rRep;
    @Autowired
    private AccessoryRepository accRep;

    public RentalSession(){
        rental = null;
        autocamper = null;
        customer = null;
        accessories = new ArrayList<>();
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

    public ArrayList<Accessory> getAccessories() {
        return accessories;
    }
    public void setAccessories(ArrayList<Accessory> accessories) {
        this.accessories = accessories;
    }

    public boolean save(){
        AutocamperRepository aRep = new AutocamperRepository();
        CustomerRepository cRep = new CustomerRepository();
        RentalRepository rRep = new RentalRepository();
        AccessoryRepository accRep = new AccessoryRepository();

        rental.setAutocamperId(autocamper.getId());
        rental.setCustomerId(cRep.create(customer).getId());
        rental = rRep.create(rental);
        for(Accessory a : accessories){
            a.setRentalId(rental.getId());
            accRep.update(a);
        }
        return true;
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
