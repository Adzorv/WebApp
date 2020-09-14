package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public ModelAndView logout( HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute( "user" ) instanceof Employee ) {
            mav.setViewName( "redirect:/werknemer" );
        } else {
            mav.setViewName( "redirect:/login" );
        }
        session.invalidate();
        return mav;
    }
}
