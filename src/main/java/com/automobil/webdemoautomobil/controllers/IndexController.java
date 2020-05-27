package com.automobil.webdemoautomobil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home(){
        System.out.println("Hello");
        return "/index";
    }

    @GetMapping("/user")
    public String user(){
        return "/user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "/admin";
    }
}
