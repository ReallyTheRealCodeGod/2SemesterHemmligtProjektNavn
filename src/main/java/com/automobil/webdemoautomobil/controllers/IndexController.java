package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @Autowired
    AutocamperRepository autoRepo;

    @GetMapping("/")
    public String home(Model model){
        System.out.println(autoRepo.getById(1));
        model.addAttribute("auto", autoRepo.getById(1));
        model.addAttribute("autos", autoRepo.getAll());
        return "/autocamperList";
    }

    @GetMapping("/user")
    public String user(){
        return "/user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "/admin";
    }
}
