package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.*;
import com.automobil.webdemoautomobil.repositories.*;
import com.automobil.webdemoautomobil.utility.BillSession;
import com.automobil.webdemoautomobil.utility.RepoInitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;


@Controller
@RequestMapping("/maintenance")
public class MaintenanceReportController {


    private CustomerRepository customerRepository;
    private AutocamperRepository autocamperRepository;
    private RentalRepository rentalRepository;
    private MaintenanceReportRepository maintenanceReportRepository;
    private AccessoryRepository accRep;
    private VariablePricesRepository priceRepo;

    @Autowired
    public MaintenanceReportController(CustomerRepository customerRepository, AutocamperRepository autocamperRepository, RentalRepository rentalRepository,
    MaintenanceReportRepository maintenanceReportRepository, AccessoryRepository accRep, VariablePricesRepository priceRepo){
        this.customerRepository = customerRepository;
        this.autocamperRepository = autocamperRepository;
        this.rentalRepository = rentalRepository;
        this.maintenanceReportRepository = maintenanceReportRepository;
        this.accRep = accRep;
        this.priceRepo = priceRepo;
    }

    @GetMapping("")
    public String start(HttpServletRequest request, @RequestParam int id){
        BillSession bs = getSession(request);

        bs.setAutocamper(autocamperRepository.getById(id));
        bs.setRental(rentalRepository.getByParameter(Integer.toString(bs.getAutocamper().getId()), "fk_autocamper_id").get(0));
        bs.setCustomer(customerRepository.getById(bs.getRental().getCustomerId()));
        bs.setAccessoryList(accRep.getByParameter(Integer.toString(bs.getRental().getId()), "fk_rental_id"));
        bs.setPrices(priceRepo.getPrices());
        return "redirect:/maintenance/report";
    }

    @GetMapping("/report")
    public String maintenanceReport(Model model, HttpServletRequest request){
        System.out.println(getSession(request).getRental());
        System.out.println(getSession(request).getCustomer());
        model.addAttribute("sesh", getSession(request));
        return "/maintenance/report";
    }

    @GetMapping("/mechComments")
    public String comments(Model model, @RequestParam int id){
        model.addAttribute("note", maintenanceReportRepository.getById(id));
        return "/maintenance/mechComments";
    }

    @PostMapping("/fillMaintenanceReport")
    public String makeReport(@ModelAttribute MaintenanceReport report, HttpServletRequest request){
        getSession(request).setMaintenanceReport(report);
        getSession(request).save();
        return "redirect:/autocampers/list?status=3";
    }

    @PostMapping("/changeStatus")
    public String changeStatus(@ModelAttribute Autocamper autocamperFromPost){
        autocamperRepository.update(autocamperFromPost);
        return "redirect:/autocampers/list?status=3";
    }

    private BillSession getSession(HttpServletRequest request){
        BillSession session = (BillSession) request.getSession().getAttribute("session");
        if(session == null){
            session = new BillSession();
            request.getSession().setAttribute("session", session);
        }
        return session;
    }


}



