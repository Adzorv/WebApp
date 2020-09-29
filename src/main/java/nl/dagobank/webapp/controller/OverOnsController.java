package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OverOnsController {


    @GetMapping("overOns")
    public ModelAndView overOnsPageHandler(Model model) {
        ModelAndView mav = new ModelAndView("overOns");
        return mav;
    }
}
