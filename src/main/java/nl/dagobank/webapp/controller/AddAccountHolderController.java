package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes( "selectedBankAccount" )
public class AddAccountHolderController {

    @GetMapping("/addAccountHolder")
    ModelAndView addAccountHolderhandler(Model model){
        ModelAndView modelAndView = new ModelAndView("addAccountHolder");
        modelAndView.addObject("selectedBankAccount", model.getAttribute("selectedBankAccount"));
        return modelAndView;



    }

}
