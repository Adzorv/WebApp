package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OpenBankAccountSucessController {

    @GetMapping("/openBankAccountSuccess")
    public ModelAndView openBankAccountSuccessHandler() {
        ModelAndView modelAndView = new ModelAndView("openBankAccountSuccess");
        modelAndView.addObject("message", "Deze pagina moet getoond worden als een bankrekening is aangemaakt.");
        return modelAndView;
    }
}
