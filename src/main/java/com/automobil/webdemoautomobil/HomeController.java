package com.automobil.webdemoautomobil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
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
