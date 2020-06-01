package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Accessory;
import com.automobil.webdemoautomobil.repositories.AccessoryRepository;
import com.automobil.webdemoautomobil.repositories.VariablePricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Controller
@RequestMapping("/admin/accessories")
public class accessoryCrudController {

    AccessoryRepository accRep;

    @Autowired
    accessoryCrudController(AccessoryRepository accRep){
        this.accRep = accRep;
    }

    @GetMapping("")
    public String accessories(Model model){
        ArrayList<Accessory> acc = accRep.getAll();
        HashMap<Integer, Integer> amounts = new HashMap();
        HashMap<Accessory, Integer> list = new HashMap<>();

        //counts occurrences of any given accessory
        for(Accessory a: acc){
            amounts.putIfAbsent(a.getTypeId(), 0);
            amounts.put(a.getTypeId(), amounts.get(a.getTypeId()) + 1);
        }
        //creates a unique list of accessories mapped to their respective amounts
        Set<Integer> keys = amounts.keySet();
        for(Accessory a: acc){
            if(keys.contains(a.getTypeId())) {
                list.put(a, amounts.get(a.getTypeId()));
                keys.remove(a.getTypeId());
            }
        }
        model.addAttribute("accessories", list);
        return "/crudAccessories";
    }

    @PostMapping("/addAccessory")
    public String addAccessories(@ModelAttribute Accessory accessory, @RequestParam int number){
        for(int i = 0; i < number; i++) {
            accessory = accRep.create(accessory);
        }
        return "redirect:/admin/accessories";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String delete){
        ArrayList<Accessory> accessories = accRep.getByParameter(delete, "fk_accessory_type_id");
        for(Accessory a: accessories){
            accRep.delete(a);
        }
        return "redirect:/admin/accessories";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute Accessory accessory, @RequestParam String name){
        System.out.println(name);
        System.out.println(accessory);
        return "redirect:/admin/accessories";
    }
}
