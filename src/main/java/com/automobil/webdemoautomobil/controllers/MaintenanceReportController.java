package com.automobil.webdemoautomobil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MaintenanceReportController {

    @GetMapping("/autocamperList")
    public String report(){
        return "/autocamperList";
    }

    @GetMapping("/user/finishedrentals")
    public String finished(){
        return "/user/finishedrentals";
    }

    @GetMapping("/user/report")
    public String maintenanceReport(){
        return "/user/report";
    }

    @GetMapping("/user/underRepList")
    public String repList(){
        return "/user/underRepList";
    }

    @GetMapping("/user/mechComments")
    public String comments(){
        return "/user/mechComments";
    }
}

