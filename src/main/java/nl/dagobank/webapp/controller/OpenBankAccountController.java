package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OpenBankAccountController {

    @GetMapping("/openBankAccount")
    public ModelAndView openBankAccountHandler() {
        ModelAndView modelAndView = new ModelAndView("openBankAccount");
        modelAndView.addObject("message", "Klikken op de link maakt een testrekening aan.");
        return modelAndView;
    }


}
