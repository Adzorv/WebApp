package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes( "user")
public class HeadBusinessController {

    @GetMapping( "overzichtmkb" )
    public String overview( Model model ) {
        if ( model.containsAttribute( "user" )) {
            Employee employee = (Employee) model.getAttribute( "user" );
            if ( employee != null && employee.getRole().equals( "HoofdMKB" )) {
                return "overviewHeadBusiness";
            } else {
                return "geenToegang";
            }
        } else {
            return "geenToegang";
        }
    }
}
