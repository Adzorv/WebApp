package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes( "user" )
public class LoginSuccessController {

    @GetMapping("loginsuccess")
    public String loginSuccess( @ModelAttribute Model model) {
        Customer customer = (Customer)model.getAttribute( "customer" );
        if (customer != null) {
            System.out.println(customer.getUserName());
        } else {
            System.out.println("customer == null");
        }

        return "login_success";
    }

}
