package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.TestForm;
import nl.dagobank.webapp.domain.Customer;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes( "user" )
public class OverviewController {

    @GetMapping("overview")
    public String overview(Model model) {
        Customer user = (Customer)model.getAttribute( "user" );
        System.out.println(user);
        return "overview";
    }
}
