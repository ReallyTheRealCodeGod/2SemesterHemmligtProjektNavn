package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.models.AutocamperType;
import com.automobil.webdemoautomobil.models.BuiltInFeature;
import com.automobil.webdemoautomobil.repositories.AutocamperTypeRepository;
import com.automobil.webdemoautomobil.repositories.BuiltInFeatureRepository;
import com.automobil.webdemoautomobil.utility.RentalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
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
    public String getAvailiable(Model model){
        ArrayList<Autocamper> autos = autoRepo.getByParameter(Integer.toString(Autocamper.AVAILABLE), "current_status");
        for(int a = 0; a < autos.size(); a++){
            for(int b = a+1; b < autos.size(); b++){
                if(autos.get(a).getType().equals(autos.get(b).getType())){
                    autos.remove(b);
                }
            }
        }
        System.out.println(autos);

        model.addAttribute("autos", autoRepo.getAll());
        model.addAttribute("autoTypes", autoTypes.getAll());
        model.addAttribute("features", featureRepo.getAll());
        return "/autocamperList";
    }

    @GetMapping("/details")
    public String details(@RequestParam int id, Model model){
        RentalSession rs = new RentalSession();
        rs.timeOut();
        Autocamper auto = autoRepo.getById(id);
        System.out.println(auto);
        model.addAttribute("auto", auto);
        return "/salesAssistant/autocamperDetails";
    }
}

