package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.IbanGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;


@Controller
@SessionAttributes( "user" )
public class OpenPrivateBankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping("openPrivateBankAccount")
    public ModelAndView openBankAccountHandler(Model model) {
        ModelAndView modelAndView = new ModelAndView("openPrivateBankAccount");
        bankAccountService.generateBankAccountNameAndPutInModel(model, modelAndView);
        return modelAndView;
    }


    @PostMapping("/openAndSaveBankAccount")
    public ModelAndView openBankAccountSuccessHandler(@RequestParam("bankAccountName") String bankAccountName, Model model, PrivateAccount privateAccount) {
        ModelAndView modelAndView = new ModelAndView("openPrivateBankAccountSuccess");
        privateAccount = bankAccountService.createAndSavePrivateAccount(bankAccountName, model);
        return modelAndView;
    }
}





