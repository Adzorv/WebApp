package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.IbanGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;


@Controller
public class OpenBankAccountSucessController {

    @Autowired
    private BankAccountDao bankAccountDao;

    @GetMapping("/openBankAccountSuccess")
    public ModelAndView openBankAccountSuccessHandler(PrivateAccount privateAccount, IbanGenerator ibanGenerator, ModelAndView modelAndView) {
        privateAccount.setAccountHolder("Sebi");
        privateAccount.setAccountName("testsebi Rekening");
        privateAccount.setBalance(new BigDecimal("25"));
        privateAccount.setIban(ibanGenerator.toString());

        bankAccountDao.save(privateAccount);
        modelAndView.setViewName("openBankAccountSuccess");
        modelAndView.addObject("message", "Deze pagina moet getoond worden als een bankrekening is aangemaakt.");
        modelAndView.addObject("iban", privateAccount.getIban());
        return modelAndView;
    }
}
