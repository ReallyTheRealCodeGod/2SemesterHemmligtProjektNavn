package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repositories.AutocamperRepository;

import java.sql.SQLException;

@Controller
@RequestMapping("/autocampers")
public class AutocamperDetails {
    @Autowired
    AutocamperRepository autoRepo;

    @GetMapping("/hello")
    public String list(Model model){
        model.addAttribute("autos", autoRepo.getAll());
        System.out.println("hello");
        return "autocamperList";
    }
}
