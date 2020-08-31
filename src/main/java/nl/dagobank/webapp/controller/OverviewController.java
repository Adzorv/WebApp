package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.TestForm;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OverviewController {

    @GetMapping("overview")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView( "overview" );
        return mav;
    }
}
