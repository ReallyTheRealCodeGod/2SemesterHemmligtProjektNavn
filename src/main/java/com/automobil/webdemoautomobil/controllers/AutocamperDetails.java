package com.automobil.webdemoautomobil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;

@Controller
@RequestMapping("/autocampers")
public class AutocamperDetails {

    @GetMapping("")
    public String list(Model model){
       // model.addAttribute("autos", autoRepo.getAll());
        System.out.println("hello");
        return "autocamperList";
    }
}
