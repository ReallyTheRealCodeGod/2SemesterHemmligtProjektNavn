package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.models.AutocamperType;
import com.automobil.webdemoautomobil.models.BuiltInFeature;
import com.automobil.webdemoautomobil.repositories.AutocamperTypeRepository;
import com.automobil.webdemoautomobil.repositories.BuiltInFeatureRepository;
import com.automobil.webdemoautomobil.repositories.UserRepository;
import com.automobil.webdemoautomobil.utility.RentalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/autocampers")
public class AutocamperController {

    AutocamperRepository autoRepo;
    AutocamperTypeRepository autoTypes;
    BuiltInFeatureRepository featureRepo;
    UserRepository userRepo;

    @Autowired
    AutocamperController(AutocamperRepository autoRepo, AutocamperTypeRepository autoTypes, BuiltInFeatureRepository featureRepo, UserRepository userRepo){
        this.featureRepo = featureRepo;
        this.autoRepo = autoRepo;
        this.autoTypes = autoTypes;
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public String getAvailiable(Model model){
        ArrayList<Autocamper> autos = autoRepo.getByParameter(Integer.toString(Autocamper.AVAILABLE), "current_status");
        for(int a = 0; a < autos.size(); a++){
            for(int b = a+1; b < autos.size(); b++){
                if(autos.get(a).getType().equals(autos.get(b).getType())){
                    autos.remove(b);
                }
            }
        }
        model.addAttribute("autos", autoRepo.getAll());
        model.addAttribute("autoTypes", autoTypes.getAll());
        model.addAttribute("features", featureRepo.getAll());
        return "/autocampers/available";
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String status, HttpServletRequest request, Model model){
        model.addAttribute("autoTypes", autoTypes.getAll());
        model.addAttribute("features", featureRepo.getAll());

        String role = null;
        for(String r: userRepo.getAuthorities()){
            if(request.isUserInRole(r)) {
                role = r;
            }
        }

        String title = "Alle";
        if(status != null) {
            switch(status){
                case "1":{title = "Ledige"; break;}
                case "2":{title = "Reserveret"; break;}
                case "3":{title = "Vedligeholdes"; break;}
                case "4":{title = "Udlejet"; break;}
                case "5":{title = "Reparation"; break;}
            }
            model.addAttribute("autos", autoRepo.getByParameter(status, "current_status"));
        }else{
            model.addAttribute("autos", autoRepo.getAll());
        }
        model.addAttribute("role", role);
        model.addAttribute("title", title);
        return "autocampers/list";

    }

    @GetMapping("/details")
    public String details(@RequestParam int id, Model model){
        model.addAttribute("auto", autoRepo.getById(id));
        return "/autocampers/details";

    }
}

