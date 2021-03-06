package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomepageController {

    public HomepageController() {
        super();
    }

    @GetMapping("/")
    public String homepageHandlerDefault() {
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepageHandler() {
        return "homepage";
    }

}
