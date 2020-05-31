package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import com.automobil.webdemoautomobil.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @Autowired
    AutocamperRepository autoRepo;
    CustomerRepository custRepo;

    @GetMapping("/")
    public String home(){
        return "/index";
    }

    @GetMapping("/user")
    public String user(){
        return "/user";
    }

    @GetMapping("/salesAssistant")
    public String sales(){
        return "/salesAssistant";
    }
}
