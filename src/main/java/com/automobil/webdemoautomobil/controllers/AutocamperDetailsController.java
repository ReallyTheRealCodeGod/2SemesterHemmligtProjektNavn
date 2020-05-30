package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.models.BuiltInFeature;
import com.automobil.webdemoautomobil.repositories.AutocamperTypeRepository;
import com.automobil.webdemoautomobil.repositories.BuiltInFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;

import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping("/autocampers")
public class AutocamperDetailsController{

    AutocamperRepository autoRepo;
    AutocamperTypeRepository autoTypes;
    BuiltInFeatureRepository featureRepo;

    @Autowired
    AutocamperDetailsController(AutocamperRepository autoRepo, AutocamperTypeRepository autoTypes, BuiltInFeatureRepository featureRepo){
        this.featureRepo = featureRepo;
        this.autoRepo = autoRepo;
        this.autoTypes = autoTypes;

    }

    @GetMapping("")
    public String list(Model model){
        ArrayList<BuiltInFeature> features = featureRepo.getAll();
        for(BuiltInFeature feature: features) {
            System.out.println(feature.getName());
        }
        model.addAttribute("autos", autoRepo.getAll());
        model.addAttribute("autoTypes", autoTypes.getAll());
        return "/autocamperList";
    }
}

