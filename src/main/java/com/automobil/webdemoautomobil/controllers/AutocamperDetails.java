package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Autocamper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;

@Controller
@RequestMapping("/Autocampers")
public class AutocamperDetails {

    @Autowired
    AutocamperRepository autoRepo;

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("auto", autoRepo.getById(1));
        model.addAttribute("autos", autoRepo.getAll());
        return "/autocamperList";
    }
}

