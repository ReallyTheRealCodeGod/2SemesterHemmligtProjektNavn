package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Accessory;
import com.automobil.webdemoautomobil.repositories.AccessoryRepository;
import com.automobil.webdemoautomobil.repositories.VariablePricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
        model.addAttribute("accessories", accRep.getAllTypes());
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
    public String edit(@RequestParam int amount, @RequestParam int typeId, @RequestParam String name, @RequestParam  String description, @RequestParam int price){
        Accessory acc = new Accessory();
        acc.setTypeId(typeId);
        acc.setName(name);
        acc.setDescription(description);
        acc.setPrice(price);
        System.out.println(acc + " " + amount);
        try {
            accRep.updateType(acc);
        }catch(SQLException sql){
            sql.printStackTrace();
        }

        //compare input amount with database amount.
        HashMap<Accessory, Integer> types = accRep.getAllTypes();
        Set<Accessory> keys = types.keySet();
        for(Accessory key: keys){
            if(key.getTypeId() == typeId){
                //if new amount is less than old amount accessories have been lost and should be deleted from the system
                if(amount < types.get(key)){
                    int difference = types.get(key) - amount;
                    ArrayList<Accessory> a = accRep.getByParameter(Integer.toString(typeId), "fk_accessory_type_id");
                    for(int i = 0; i < difference; i++) {
                        accRep.delete(a.get(i));
                    }
                }
                // If new amount is more than old amount accessories have been added and should be created in the system
                if(amount > types.get(key)){
                    int difference = amount - types.get(key);
                    for(int i = 0; i < difference; i++){
                        accRep.create(acc);
                    }
                }
            }
        }

        return "redirect:/admin/accessories";
    }
}
