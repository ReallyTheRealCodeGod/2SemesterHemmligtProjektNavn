package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.VariablePrices;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import com.automobil.webdemoautomobil.repositories.AutocamperTypeRepository;
import com.automobil.webdemoautomobil.repositories.BuiltInFeatureRepository;
import com.automobil.webdemoautomobil.repositories.VariablePricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autocampers")
public class RentalController {

    AutocamperRepository autoRepo;
    AutocamperTypeRepository autoTypes;
    BuiltInFeatureRepository featureRepo;

    @Autowired
    RentalController(AutocamperRepository autoRepo, AutocamperTypeRepository autoTypes, BuiltInFeatureRepository featureRepo){
        this.featureRepo = featureRepo;
        this.autoRepo = autoRepo;
        this.autoTypes = autoTypes;
    }

    @GetMapping("/")
    public String variablePrices(Model model){
        return "/variablePricesView";
    }

    @GetMapping("/rental")
    public String rental(Model model){
        model.addAttribute("autos", autoRepo.getAll());
        model.addAttribute("autoTypes", autoTypes.getAll());
        model.addAttribute("features", featureRepo.getAll());
        return "/autocamperList";
    }

    @GetMapping("/rental/checkoutpage")
    public String checkoutpage(Model model) { return "/udlejningsinfo"; }


}
