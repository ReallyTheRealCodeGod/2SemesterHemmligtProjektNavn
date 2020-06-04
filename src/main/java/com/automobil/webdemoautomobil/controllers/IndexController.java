package com.automobil.webdemoautomobil.controllers;

import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import com.automobil.webdemoautomobil.repositories.CustomerRepository;
import com.automobil.webdemoautomobil.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private UserRepository userRep;

    @Autowired
    public IndexController(UserRepository userRep){
        this.userRep = userRep;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request){
        String role = null;
        for(String r: userRep.getAuthorities()){
            if(request.isUserInRole(r)) {
                role = r;
            }
        }
        if(role != null){
            switch(role){
                case "ROLE_USER":{return "/mechanic";}
                case "ROLE_ADMIN":{return "redirect:/admin";}
                case "ROLE_SALES":{return "/salesAssistant";}
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/mechanic")
    public String user(){
        return "/mechanic";
    }

    @GetMapping("/salesAssistant")
    public String sales(){
        return "/salesAssistant";
    }
}
