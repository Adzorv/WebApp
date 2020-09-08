package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes( "bankAccountHolderToken" )
public class BankAccountHolderTokenSuccessController {

HttpSession session;

    @Autowired
    BankAccountHolderTokenSuccessController( HttpSession session ){
        this.session = session;
    }

    @GetMapping("bankAccountHolderTokenSuccess")
    public ModelAndView bankAccountHolderTokenSuccessHandler(Model model){

        BankAccountHolderToken bankAccountHolderToken = (BankAccountHolderToken)session.getAttribute("bankAccountHolderToken");
        Customer customerToAdd = bankAccountHolderToken.getBecomingSecundaryAccountHolder();
        PrivateAccount accountToAdd = bankAccountHolderToken.getAccountToAdd();

        ModelAndView modelAndView = new ModelAndView("bankAccountHolderTokenSuccess");
        modelAndView.addObject("username", customerToAdd.getUserName());
        modelAndView.addObject("iban",accountToAdd.getIban());
        modelAndView.addObject("bankAccountHolderToken", bankAccountHolderToken);

        return modelAndView;
    }
}
