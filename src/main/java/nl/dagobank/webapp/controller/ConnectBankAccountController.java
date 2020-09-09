package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ConnectBankAccountForm;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.BankAccountHolderTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes("user")
public class ConnectBankAccountController {

    @Autowired
    BankAccountHolderTokenService bankAccountHolderTokenService;



    @GetMapping("connectBankAccount")
    public String connectBankAccountLoaderHandler(Model model){
        model.addAttribute("error", "");
        return "connectBankAccount";
    }

    @PostMapping("connectBankAccount")
    public ModelAndView connectBankAccountHandler(@ModelAttribute ConnectBankAccountForm connectBankAccountForm, Model model){
        ModelAndView modelAndView = new ModelAndView("bankAccountConnectionSuccess");
        Customer user = (Customer)model.getAttribute( "user" );
        String codeFromForm = connectBankAccountForm.getConnectionCode();
        String ibanFromForm = connectBankAccountForm.getIban();

        if (bankAccountHolderTokenService.existsValidToken(user, ibanFromForm, codeFromForm)){
            List<BankAccountHolderToken> validTokens = bankAccountHolderTokenService.getValidTokens(user, ibanFromForm, codeFromForm);
            BankAccount bankAccountToAddCustomer = validTokens.get(0).getAccountToAdd();
            bankAccountToAddCustomer.getSecondaryAccountHolders().add(user);
            bankAccountHolderTokenService.deleteTokens(validTokens);
        } else {
            modelAndView.addObject("error", "Niet gelukt, IBAN en/of code zijn niet correct of je bent niet geautoriseert door Rekeninghouder.");
            modelAndView.setViewName("connectBankAccount");
        }
        return modelAndView;
    }

}
