package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OurTeamController {

    @GetMapping("/ourTeam")
    public String startFinder( Model model ) {
        return "ourTeam";
    }


}
