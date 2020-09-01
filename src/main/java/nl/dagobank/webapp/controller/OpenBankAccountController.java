package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes( "user" )
public class OpenBankAccountController {

    @GetMapping("/openBankAccount")
    public ModelAndView openBankAccountHandler(Model model) {
        ModelAndView modelAndView = new ModelAndView("openBankAccount");
        Customer user = (Customer)model.getAttribute( "user" );
        modelAndView.addObject("message", "Op dit moment kunnen alleen particulier rekeningen worden geopend, klik de link om een rekening te openen.");
        modelAndView.addObject("customerName", user.getFirstName());
        return modelAndView;
    }


}
