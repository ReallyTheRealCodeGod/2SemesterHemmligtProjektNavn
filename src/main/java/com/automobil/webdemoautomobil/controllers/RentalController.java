package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Accessory;
import com.automobil.webdemoautomobil.models.Rental;
import com.automobil.webdemoautomobil.models.VariablePrices;
import com.automobil.webdemoautomobil.repositories.*;
import com.automobil.webdemoautomobil.utility.RentalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.ArrayList;

@Controller
@RequestMapping("/rental")
public class RentalController {

    AutocamperRepository autoRepo;
    AutocamperTypeRepository autoTypes;
    BuiltInFeatureRepository featureRepo;
    AccessoryRepository accessoriesList;

    @Autowired
    RentalController(AutocamperRepository autoRepo, AutocamperTypeRepository autoTypes, BuiltInFeatureRepository featureRepo,AccessoryRepository accessoriesList){
        this.featureRepo = featureRepo;
        this.autoRepo = autoRepo;
        this.autoTypes = autoTypes;
        this.accessoriesList = accessoriesList;
    }

    @GetMapping("/")
    public String variablePrices(Model model){
        return "/variablePricesView";
    }

    @GetMapping("/listauto")
    public String rental(Model model){
        model.addAttribute("autos", autoRepo.getAll());
        model.addAttribute("autoTypes", autoTypes.getAll());
        model.addAttribute("features", featureRepo.getAll());
        return "/autocamperList";
    }


    @PostMapping("/accessoriesloc")
    public String accessoriesloc(Model model, @RequestParam int id) {
        RentalSession rs = new RentalSession();
        rs.setAutocamper(autoRepo.getById(id));
        model.addAttribute("session", rs);
        model.addAttribute("accessories", accessoriesList.getAllTypes());
        return "/udlejningsinfo";

    }





    @PostMapping("/checkout")
    public String checkout(Model model, @RequestParam(name="accessoryCheck", required = false) int[] accessoryCheck, @RequestParam RentalSession session,@ModelAttribute Rental newRental ) {
        newRental.setAutocamperId(session.getAutocamper().getId());

        if(accessoryCheck != null) {
            for (int i : accessoryCheck) {
                ArrayList<Accessory> all = accessoriesList.getByParameter(Integer.toString(i), "fk_accessory_type_id");

                for (Accessory a : all) {
                    if (a.getRentalId() == 0) {
                        a.setRentalId(newRental.getId());
                        //accessoriesList.update(a);
                        continue;
                    }
                }
            }
        }

        /*RentalSession rs = new RentalSession();
       // rs.setAutocamper(autoRepo.getById(id));
        model.addAttribute("session", rs);*/

        return "/salesAssistant/checkOut"; }


}
