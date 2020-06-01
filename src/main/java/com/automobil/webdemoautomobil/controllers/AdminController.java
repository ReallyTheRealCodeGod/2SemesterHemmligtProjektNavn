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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    VariablePricesRepository vpr;



    @Autowired
    AdminController(VariablePricesRepository vpr){
        this.vpr = vpr;
    }

    @GetMapping("")
    public String admin(){
        return "/admin";
    }

    @GetMapping("/prices")
    public String variablePrices(Model model){
        VariablePrices vp = vpr.getPrices();
        System.out.println(vp);
        model.addAttribute("prices", vp);
        return "/variablePricesView";
    }


    @PostMapping("/changePrices")
    public String changePrices(@ModelAttribute VariablePrices prices){
        System.out.println(prices);
        vpr.editPrices(prices);
        return "redirect:/admin/prices";
    }

}
