package com.automobil.webdemoautomobil.controllers;


import com.automobil.webdemoautomobil.models.Season;
import com.automobil.webdemoautomobil.models.VariablePrices;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import com.automobil.webdemoautomobil.repositories.AutocamperTypeRepository;
import com.automobil.webdemoautomobil.repositories.BuiltInFeatureRepository;
import com.automobil.webdemoautomobil.repositories.VariablePricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "/admin/admin";
    }

    @GetMapping("/prices")
    public String variablePrices(Model model){
        VariablePrices vp = vpr.getPrices();
        System.out.println(vp);
        model.addAttribute("seasons", vp.getSeasons());
        model.addAttribute("prices", vp);
        return "/admin/variablePricesView";
    }

    @PostMapping("/addSeason")
    public String addSeason(@ModelAttribute Season season){
        vpr.addSeason(season);
        return "redirect:/admin/prices";
    }

    @GetMapping("/delete")
    public String deletePrice(@RequestParam String name){
        vpr.deleteSeason(name);
        return "redirect:/admin/prices";
    }
    @PostMapping("/changePrices")
    public String changePrices(@ModelAttribute VariablePrices prices){
        vpr.editPrices(prices);
        return "redirect:/admin/prices";
    }

}
