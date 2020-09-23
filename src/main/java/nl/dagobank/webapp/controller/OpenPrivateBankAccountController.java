package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes( "user" )
public class OpenPrivateBankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping("openPrivateBankAccount")
    public ModelAndView openBankAccountHandler(Model model) {
        ModelAndView modelAndView = new ModelAndView("openPrivateBankAccount");
        Customer user = (Customer) model.getAttribute("user");
        String bankAccountName = bankAccountService.generateBankAccountNameFromUserNameAndNumberOfAccounts(user);
        modelAndView.addObject("bankAccountName", bankAccountName);
        return modelAndView;
    }

    @PostMapping("/openAndSaveBankAccount")
    public ModelAndView openBankAccountSuccessHandler(@RequestParam("bankAccountName") String bankAccountName, Model model, PrivateAccount privateAccount) {
        Customer user = (Customer) model.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("openPrivateBankAccountSuccess");
        privateAccount = bankAccountService.createAndSavePrivateAccount(bankAccountName, user);
        return modelAndView;
    }
}





