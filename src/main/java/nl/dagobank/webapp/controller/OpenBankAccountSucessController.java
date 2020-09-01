package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.IbanGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;


@Controller
@SessionAttributes( "user" )
public class OpenBankAccountSucessController {

    @Autowired
    private BankAccountDao bankAccountDao;

    @GetMapping("/openBankAccountSuccess")
    public ModelAndView openBankAccountSuccessHandler(PrivateAccount privateAccount, IbanGenerator ibanGenerator, ModelAndView modelAndView, Model model) {
        Customer user = (Customer)model.getAttribute( "user" );
        privateAccount.setAccountHolder(user);
        privateAccount.setAccountName("Rekening van " + user.getFirstName());
        privateAccount.setBalance(new BigDecimal("25"));
        privateAccount.setIban(ibanGenerator.toString());

        bankAccountDao.save(privateAccount);

        modelAndView.setViewName("openBankAccountSuccess");
        modelAndView.addObject("message", "De volgende rekening is aangemaak:");
        modelAndView.addObject("bankaccount", privateAccount.toString());

        return modelAndView;
    }
}
