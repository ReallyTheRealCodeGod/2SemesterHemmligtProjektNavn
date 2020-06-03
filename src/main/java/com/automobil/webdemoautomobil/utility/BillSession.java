package com.automobil.webdemoautomobil.utility;

import com.automobil.webdemoautomobil.models.*;
import com.automobil.webdemoautomobil.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BillSession {
    Autocamper autocamper;
    Rental rental;
    ArrayList<Accessory> accessoryList;
    VariablePrices prices;
    Customer customer;
    MaintenanceReport maintenanceReport;

    public BillSession(){
        autocamper = null;
        rental = null;
        accessoryList = null;
        customer = null;
        prices = null;
        maintenanceReport = null;
    }


    public MaintenanceReport getMaintenanceReport() {
        return maintenanceReport;
    }

    public void setMaintenanceReport(MaintenanceReport maintenanceReport) {
        this.maintenanceReport = maintenanceReport;
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

    public void save() throws SQLException{
        BillRepository billrepo = new BillRepository();
        AccessoryRepository accRep = new AccessoryRepository();
        AutocamperRepository autoRep = new AutocamperRepository();
        RentalRepository rentRep = new RentalRepository();
        CustomerRepository custRep = new CustomerRepository();

        maintenanceReport.setAutocamperId(autocamper.getId());

        int accessoryCost = 0;
        for(Accessory a: accessoryList){
            accessoryCost += a.getPrice();
        }

        int dayPrice = autocamper.getType().getPrice();
        int days = (int) (rental.getEndDate().toEpochDay() - rental.getStartDate().toEpochDay());
        int rentalPrice = (days * (dayPrice * (100/prices.getCurrentSeason().getSurchargePercentage() + 1)));
        int totalprice = rentalPrice + accessoryCost + maintenanceReport.getCleaningPrice() + maintenanceReport.getRepairCost();

        Bill bill = new Bill(LocalDate.now(), customer.getFirstName(), customer.getLastName(), customer.getPostalCode(),
                customer.getStreetName(),customer.getHouseNr(),customer.getFloor(), accessoryCost, rentalPrice, totalprice);


            bill = billrepo.create(bill);
            for(Accessory a: accessoryList) {
                a.setRentalId(0);
                accRep.update(a);
            }
            custRep.delete(customer);

            autocamper.setStatus(1);
            autoRep.update(autocamper);

            rentRep.delete(rental);
    };
}
