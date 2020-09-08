package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@SessionAttributes( "user")
public class HeadBusinessController {

    @GetMapping( "overzichtmkb" )
    public String overview( Model model ) {
        if ( model.containsAttribute( "user" )) {
            System.out.println("Model/session heeft ingelogde user");
            Employee employee = (Employee) model.getAttribute( "user" );
            if ( employee != null && employee.getRole().equals( "HoofdMKB" )) {
                return "overviewHeadBusiness";
            } else {
                return "geenToegang";
            }
        } else {
            System.out.println("Model/session heeft geen ingelogde user");
            return "geenToegang";
        }
    }
}
