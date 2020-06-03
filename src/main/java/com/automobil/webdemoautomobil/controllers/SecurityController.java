package com.automobil.webdemoautomobil.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/login")
    public String security(){
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/logout";
    }
}
