package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ConnectBankAccountForm;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.BankAccountHolderTokenService;
import nl.dagobank.webapp.service.BankAccountService;
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

    @Autowired
    BankAccountService bankAccountService;

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
            makeSureAdditionalAccountHolderAddedAndDeleteTokens(modelAndView, user, codeFromForm, ibanFromForm);
        } else {
            String errorNietCorrect = "Niet gelukt, IBAN en/of code zijn niet correct of je bent niet geautoriseert door Rekeninghouder.";
            setErrorMessageAndPrepareForOutput(modelAndView, errorNietCorrect);
        }
        return modelAndView;
    }

    private void setErrorMessageAndPrepareForOutput(ModelAndView modelAndView, String s) {
        modelAndView.addObject("error", s);
        modelAndView.setViewName("connectBankAccount");
    }

    private void makeSureAdditionalAccountHolderAddedAndDeleteTokens(ModelAndView modelAndView, Customer user, String codeFromForm, String ibanFromForm) {
        List<BankAccountHolderToken> validTokens = bankAccountHolderTokenService.getValidTokens(user, ibanFromForm);
        BankAccount bankAccountToAddCustomer = validTokens.get(0).getAccountToAdd();
        if (!bankAccountService.isCustomerSecondAccountHolder(user, bankAccountToAddCustomer)) {
            bankAccountToAddCustomer.getSecondaryAccountHolders().add(user);
        } else {
            String errorAlreadySecundairyAccountHolder = "Je bent al een rekeninghouder van deze rekening.";
            setErrorMessageAndPrepareForOutput(modelAndView, errorAlreadySecundairyAccountHolder);
        }
        bankAccountHolderTokenService.deleteTokens(validTokens);
    }
}
