package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static nl.dagobank.webapp.backingbeans.ContactForm.subjects;

@Controller
public class OverOnsController {


    @GetMapping("overOns")
    public ModelAndView overOnsPageHandler(Model model) {
        ModelAndView mav = new ModelAndView("overOns");
        return mav;
    }
}
