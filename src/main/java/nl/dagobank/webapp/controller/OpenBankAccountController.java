package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.BankAccountNameForm;
import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.IbanGenerator;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;


@Controller
@SessionAttributes( "user" )
public class OpenBankAccountController {


    @GetMapping("/openBankAccount")
    public ModelAndView openBankAccountHandler(Model model) {
        ModelAndView modelAndView = new ModelAndView("openBankAccount");
        Customer user = (Customer) model.getAttribute("user");
        modelAndView.addObject("message", "Op dit moment kunnen alleen particulier rekeningen worden geopend, klik de link om een rekening te openen.");
        modelAndView.addObject("customerName", user.getFirstName());
        modelAndView.addObject("bankAccountNameForm", new BankAccountNameForm());
        return modelAndView;
    }


}





