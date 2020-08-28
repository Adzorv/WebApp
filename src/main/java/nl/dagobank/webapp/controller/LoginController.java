package nl.dagobank.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    public LoginController() { super();
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView( "login" );
        return mav;
    }


}
