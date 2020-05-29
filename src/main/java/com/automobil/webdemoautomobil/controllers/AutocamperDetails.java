package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.repositories.AutocamperTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;

@Controller
@RequestMapping("/autocampers")
public class AutocamperDetails {

    AutocamperRepository autoRepo;
    AutocamperTypeRepository autoTypes;

    @Autowired
    AutocamperDetails(AutocamperRepository autoRepo, AutocamperTypeRepository autoTypes){
        this.autoRepo = autoRepo;
        this.autoTypes = autoTypes;

    }

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("autos", autoRepo.getAll());
        model.addAttribute("autoTypes", autoTypes.getAll());
        return "/autocamperList";
    }
}

