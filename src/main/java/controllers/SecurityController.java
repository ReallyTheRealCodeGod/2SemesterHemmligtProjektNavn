package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/login")
    public String security(){
        return "/index";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/logout";
    }
}
