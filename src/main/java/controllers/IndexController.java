package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utility.JDBCConnection;

@Controller
public class IndexController {

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
