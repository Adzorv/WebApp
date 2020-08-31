package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ContactForm;
import nl.dagobank.webapp.backingbeans.TestForm;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ContactpageController {

    @GetMapping("contact")
    public ModelAndView contact() {
        ModelAndView mav = new ModelAndView("contact");
        mav.addObject("contactform", new ContactForm());
        return mav;
    }

    @PostMapping("contact")
    public ModelAndView welcomeHandler(@ModelAttribute ContactForm contactform) {
        ModelAndView mav = new ModelAndView("contact_form_send");
        System.out.println(contactform.getFirstName());
        return mav;
    }

}

