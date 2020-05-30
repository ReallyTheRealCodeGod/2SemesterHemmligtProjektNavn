package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.*;
import com.automobil.webdemoautomobil.repositories.*;
import com.automobil.webdemoautomobil.utility.RepoInitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Controller
@RequestMapping("/user")
public class MaintenanceReportController {


    private CustomerRepository customerRepository;
    private AutocamperRepository autocamperRepository;
    private RentalRepository rentalRepository;
    private MaintenanceReportRepository maintenanceReportRepository;
    private AutocamperTypeRepository autocamperTypeRepository;

    @Autowired
    public MaintenanceReportController(CustomerRepository customerRepository, AutocamperRepository autocamperRepository, RentalRepository rentalRepository,
    MaintenanceReportRepository maintenanceReportRepository, AutocamperTypeRepository autocamperTypeRepository){
        this.customerRepository = customerRepository;
        this.autocamperRepository = autocamperRepository;
        this.rentalRepository = rentalRepository;
        this.maintenanceReportRepository = maintenanceReportRepository;
        this.autocamperTypeRepository = autocamperTypeRepository;

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
    public String repList(Model model){

        ArrayList<Autocamper> autocamper = autocamperRepository.getByParameter(Integer.toString(Autocamper.NEEDS_FIXING), "current_status");
        ArrayList<MaintenanceReport> maintenance = maintenanceReportRepository.getAll();

        model.addAttribute("autocampers", autocamper);
        model.addAttribute("maintenances", maintenance);
        return "/user/underRepList";
    }

    @GetMapping("/mechComments")
    public String comments(Model model, @RequestParam int id){
        model.addAttribute("note", maintenanceReportRepository.getById(id));
        return "/user/mechComments";
    }

    @PostMapping("/123")
    public String test(){
        return "";
    }

}



