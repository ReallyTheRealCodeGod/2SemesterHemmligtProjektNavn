package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utility.JDBCConnection;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model){
        String url = "hello";
        System.out.println(JDBCConnection.user + ": this is user");
        model.addAttribute("url", url);
        return "index";
    }
}
