package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Accessory;
import com.automobil.webdemoautomobil.models.Customer;
import com.automobil.webdemoautomobil.models.Rental;
import com.automobil.webdemoautomobil.models.VariablePrices;
import com.automobil.webdemoautomobil.repositories.*;
import com.automobil.webdemoautomobil.utility.RentalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/rental")
public class RentalController {

    AutocamperRepository autoRepo;
    AccessoryRepository accessoriesList;
    RentalRepository rentalRepo;
    CustomerRepository customerRepo;

    @Autowired
    RentalController(AutocamperRepository autoRepo, CustomerRepository customerRepo,AccessoryRepository accessoriesList, RentalRepository rentalRepo){
        this.rentalRepo = rentalRepo;
        this.autoRepo = autoRepo;
        this.accessoriesList = accessoriesList;
        this.customerRepo = customerRepo;
    }

    @PostMapping("")
    public String addAutocamper(@RequestParam int id, HttpServletRequest request){
        RentalSession rs = getSession(request);
        rs.setAutocamper(autoRepo.getById(id));

        return "redirect:/rental/customer";
    }

    @GetMapping("/rentalDetails")
    public String rentalDetails(Model model) {
        model.addAttribute("accessories", accessoriesList.getAllTypes());
        return "/rental/rentalInfo";
    }
    @PostMapping("/addRental")
    public String addRental(HttpServletRequest request, @RequestParam(name="accessoryCheck", required = false) int[] accessoryCheck, @ModelAttribute Rental rental){
        RentalSession rs = getSession(request);
        rs.setRental(rental);
        if(accessoryCheck != null) {
            for (int i : accessoryCheck) {
                ArrayList<Accessory> all = accessoriesList.getByParameter(Integer.toString(i), "fk_accessory_type_id");
                for (Accessory a : all) {
                    if (a != null && a.getRentalId() == 0) {
                        rs.getAccessories().add(a);
                        continue;
                    }
                }
            }
        }
        return "redirect:/rental/confirmation";
    }

    @GetMapping("/customer")
    public String customerInfo(HttpServletRequest request, Model model){
        model.addAttribute("customer", getSession(request).getCustomer());
        return "/rental/customerInfo";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, HttpServletRequest request){
        getSession(request).setCustomer(customer);
        return "redirect:/rental/rentalDetails";
    }

    @GetMapping("/confirmation")
    public String rentalConfirmation(HttpServletRequest request, Model model){
        model.addAttribute("rentalSession", getSession(request));
        return "/rental/rentalConfirmation";
    }

    @GetMapping("/save")
    public String save(HttpServletRequest request){
        getSession(request).save();
        return "redirect:/";
    }

    private RentalSession getSession(HttpServletRequest request) {
        RentalSession rs = (RentalSession) request.getSession().getAttribute("session");
        if(rs == null){
            try {
                rs = new RentalSession();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
            request.getSession().setAttribute("session", rs);
        }
        System.out.println(rs);
        return rs;
    }
}
