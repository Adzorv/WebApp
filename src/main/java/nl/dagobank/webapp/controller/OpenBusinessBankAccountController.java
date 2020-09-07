package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.BankAccountNameForm;
import nl.dagobank.webapp.backingbeans.OpenBusinesAccountForm;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BusinessAccount;
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
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class OpenBusinessBankAccountController {
    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    IbanGenerator ibanGenerator;


    @GetMapping("openBusinessBankAccount")
    public ModelAndView openBusinessBankAccountHandler() {
        ModelAndView openBusinessAccountPage = new ModelAndView("openBusinessBankAccount");
        openBusinessAccountPage.addObject("openBusinessBankAccountForm", new OpenBusinesAccountForm());
        return openBusinessAccountPage;
    }

    @PostMapping("businessBankAccountOpened")
    public ModelAndView openBusinessAccountSuccessfulHandler(@ModelAttribute OpenBusinesAccountForm openBusinesAccountForm, Model model, BusinessAccount businessAccount) {
        ModelAndView businessBankAccountOpenened = new ModelAndView("openBusinessBankAccountSuccessful");
        Customer customer = (Customer) model.getAttribute("user");//FIXME: check how this works
        businessAccount.setAccountHolder(customer);
        businessAccount.setBalance(new BigDecimal("25"));
        Iban iban = ibanGenerator.createIban();
        businessAccount.setIban(iban.toString());
        bankAccountDao.save(businessAccount);
        return businessBankAccountOpenened;
    }

}
