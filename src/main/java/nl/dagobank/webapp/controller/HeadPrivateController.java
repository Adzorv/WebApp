package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeadPrivateController {

    @GetMapping( "overzichtparticulier" )
    public ModelAndView privateOverview() {
        return new ModelAndView( "overviewHeadPrivate" );
    }
}
