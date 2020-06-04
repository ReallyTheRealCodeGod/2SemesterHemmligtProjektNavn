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
        float surcharge = 0;
        if(prices.getCurrentSeason() != null){surcharge = (100/prices.getCurrentSeason().getSurchargePercentage());}
        System.out.println(surcharge);
        int rentalPrice = (int) (days * (dayPrice * (surcharge + 1)));
        int totalprice = rentalPrice + accessoryCost + maintenanceReport.getCleaningPrice() + maintenanceReport.getRepairCost() ;
        if(maintenanceReport.getFuelGauge() <= 50){
            totalprice += prices.getFuelPrice();
        }
        Bill bill = new Bill();
        bill.setBillingDate(LocalDate.now());
        bill.setCustomerFirstName(customer.getFirstName());
        bill.setCustomerLastName(customer.getLastName());
        bill.setPostalCode(customer.getPostalCode());
        bill.setStreetName(customer.getStreetName());
        bill.setStreetNr(customer.getHouseNr());
        bill.setApartmentFloor(customer.getFloor());
        bill.setAccessoryCost(accessoryCost);
        bill.setRentalCost(rentalPrice);
        bill.setTotalPrice(totalprice);

        System.out.println(bill.getRentalCost());
            bill = billrepo.create(bill);
            for(Accessory a: accessoryList) {
                a.setRentalId(0);
                accRep.update(a);
            }

            rentRep.delete(rental);
            custRep.delete(customer);
            autocamper.setStatus(Autocamper.AVAILABLE);
            autoRep.update(autocamper);
    };
}
