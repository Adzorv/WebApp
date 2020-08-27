package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomePageController {
    public HomePageController() {
        super();
    }

    @GetMapping("/")
    public String homePageHandler(){
        return "homepage";
    }

}
