package com.automobil.webdemoautomobil.controllers;


import com.automobil.webdemoautomobil.models.Accessory;
import com.automobil.webdemoautomobil.models.VariablePrices;
import com.automobil.webdemoautomobil.repositories.AccessoryRepository;
import com.automobil.webdemoautomobil.repositories.VariablePricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    VariablePricesRepository vpr;
    AccessoryRepository accRep;

    @Autowired
    AdminController(VariablePricesRepository vpr, AccessoryRepository accRep){
        this.accRep = accRep;
        this.vpr = vpr;
    }

    @GetMapping("")
    public String admin(){
        return "/admin";
    }

    @GetMapping("/prices")
    public String variablePrices(Model model){
        VariablePrices vp = vpr.getPrices();
        model.addAttribute("prices", vp);
        return "/variablePricesView";
    }

    @PostMapping("/changePrices")
    public String changePrices(@ModelAttribute VariablePrices prices){
        vpr.editPrices(prices);
        return "redirect:/admin/prices";
    }

}
