package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.models.Customer;
import com.automobil.webdemoautomobil.models.MaintenanceReport;
import com.automobil.webdemoautomobil.models.Rental;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import com.automobil.webdemoautomobil.repositories.CustomerRepository;
import com.automobil.webdemoautomobil.repositories.RentalRepository;
import com.automobil.webdemoautomobil.utility.RepoInitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
@RequestMapping("/user")
public class MaintenanceReportController {


    private CustomerRepository customerRepository;
    private AutocamperRepository autocamperRepository;
    private RentalRepository rentalRepository;

    @Autowired
    public MaintenanceReportController(CustomerRepository customerRepository, AutocamperRepository autocamperRepository, RentalRepository rentalRepository){
        this.customerRepository = customerRepository;
        this.autocamperRepository = autocamperRepository;
        this.rentalRepository = rentalRepository;

    }

    @GetMapping("/finishedrentals")
    public String finished(Model model){
        ArrayList<Autocamper> autocampers = autocamperRepository.getByParameter(Integer.toString(Autocamper.UNDER_MAINTENANCE), "current_status");
        ArrayList<Customer> customers = customerRepository.getAll();
        ArrayList<Rental> rental = rentalRepository.getAll();


        model.addAttribute("autocampers", autocampers);
        model.addAttribute("rentals", rental);
        model.addAttribute("customers", customers);

        return "/user/finishedrentals";
    }

    @GetMapping("/report")
    public String maintenanceReport(){
        return "/user/report";
    }

    @GetMapping("/underRepList")
    public String repList(){
        return "/user/underRepList";
    }

    @GetMapping("/mechComments")
    public String comments(){
        return "/user/mechComments";
    }
}



