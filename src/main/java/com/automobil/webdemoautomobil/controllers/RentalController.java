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
import java.util.ArrayList;

@Controller
@RequestMapping("/rental")
public class RentalController {

    AutocamperRepository autoRepo;
    AutocamperTypeRepository autoTypes;
    BuiltInFeatureRepository featureRepo;
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

    @GetMapping("/")
    public String variablePrices(Model model){
        return "/variablePricesView";
    }

    @GetMapping("/rentalDetails")
    public String rentalDetails(Model model,HttpServletRequest request, @RequestParam int id) {
        RentalSession rs = new RentalSession();
        rs.setAutocamper(autoRepo.getById(id));
        request.getSession().setAttribute("session", (RentalSession) rs);
        
        model.addAttribute("accessories", accessoriesList.getAllTypes());
        return "/udlejningsinfo";
    }
    
    @PostMapping("/addRental")
    public String addRental(HttpServletRequest request, @RequestParam(name="accessoryCheck", required = false) int[] accessoryCheck, @ModelAttribute Rental rental){
        RentalSession rs = (RentalSession) request.getSession().getAttribute("session");
        rental.setAutocamperId(rs.getAutocamper().getId());
        rental = rentalRepo.create(rental);
        if(accessoryCheck != null) {
            for (int i : accessoryCheck) {
                ArrayList<Accessory> all = accessoriesList.getByParameter(Integer.toString(i), "fk_accessory_type_id");
                for (Accessory a : all) {
                    if (a.getRentalId() == 0) {
                        a.setRentalId(rental.getId());
                        System.out.println(a);
                        accessoriesList.update(a);
                        continue;
                    }
                }
            }
        }
        return "redirect:/";
    }

    @PostMapping("/customer")
    public String customerInfo(Model model, HttpServletRequest request) {
       
        return "/salesAssistant/checkOut";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, HttpServletRequest request){
        customer = customerRepo.create(customer);
        RentalSession rs = (RentalSession) request.getSession().getAttribute("session");
        rs.setCustomer(customer);
        return "redirect:/rentalDetails";
    }


}
