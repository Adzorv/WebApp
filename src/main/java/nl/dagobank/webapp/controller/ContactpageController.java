package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static nl.dagobank.webapp.backingbeans.ContactForm.subjects;


@Controller
public class ContactpageController {

    public ContactpageController() {
    }

    @GetMapping("contact")
    public ModelAndView contact(Model model) {
        ModelAndView mav = new ModelAndView("contact");
        model.addAttribute("subjects", subjects);
        mav.addObject("contactform", new ContactForm());
        return mav;
    }

    @PostMapping("contact")
    public ModelAndView contact(@ModelAttribute ContactForm contactform) {
        ModelAndView mav = new ModelAndView("contactFormSend");
        System.out.println(contactform.getFirstName());
        return mav;
    }

}

