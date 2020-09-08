package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeOverviewController {

    @GetMapping( "overzichtmkb" )
    public String overview( Model model ) {
        return "employeeOverview";
    }

}
