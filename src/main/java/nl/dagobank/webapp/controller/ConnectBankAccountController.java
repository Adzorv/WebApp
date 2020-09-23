package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ConnectBankAccountForm;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.BankAccountHolderTokenService;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.FacadeServiceBankAccountHolderToken_BancAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import nl.dagobank.webapp.exception.*;

import java.util.List;

@Controller
@SessionAttributes("user")
public class ConnectBankAccountController {

    BankAccountHolderTokenService bankAccountHolderTokenService;
    BankAccountService bankAccountService;
    FacadeServiceBankAccountHolderToken_BancAccount facadeService;

    @Autowired
    public ConnectBankAccountController(FacadeServiceBankAccountHolderToken_BancAccount facadeService, BankAccountHolderTokenService bankAccountHolderTokenService, BankAccountService bankAccountService) {
        this.facadeService = facadeService;
        this.bankAccountHolderTokenService = bankAccountHolderTokenService;
        this.bankAccountService = bankAccountService;
    }



    @GetMapping("connectBankAccount")
    public String connectBankAccountLoaderHandler(Model model){
        model.addAttribute("error", "");
        return "connectBankAccount";
    }

    @PostMapping("connectBankAccount")
    public ModelAndView connectBankAccountHandler(@ModelAttribute ConnectBankAccountForm connectBankAccountForm, Model model) {
        ModelAndView modelAndView = new ModelAndView("bankAccountConnectionSuccess");
        Customer user = (Customer) model.getAttribute("user");
        String codeFromForm = connectBankAccountForm.getConnectionCode();
        String ibanFromForm = connectBankAccountForm.getIban();
        try {
            facadeService.addUserAsSecondaryAccountHolder(user, ibanFromForm, codeFromForm);
        } catch (noValidTokenException e) {
            System.out.println(e.getMessage());
            String errorNietCorrect = "IBAN en/of code zijn niet correct of je bent niet gemachtigd door de rekeninghouder.";
            setErrorMessageAndPrepareForOutput(modelAndView, errorNietCorrect);
        } catch (customerAlreadySecondaryAccountHolderException e) {
            System.out.println(e.getMessage());
            String alreadyAccountHolder = "Je bent al Mederekeninghouder.";
            setErrorMessageAndPrepareForOutput(modelAndView, alreadyAccountHolder);
        }
        return modelAndView;
    }

    private void setErrorMessageAndPrepareForOutput(ModelAndView modelAndView, String s) {
        modelAndView.addObject("error", s);
        modelAndView.setViewName("connectBankAccount");
    }



//        if (facadeService.tokenExistsAndUserIsAlreadySecundaryAccountHolder(user, ibanFromForm, codeFromForm)){
//            String alreadyAccountHolder = "Je bent al Mederekeninghouder.";
//            setErrorMessageAndPrepareForOutput(modelAndView, alreadyAccountHolder);
//        }
//
//        if (bankAccountHolderTokenService.existsValidToken(user, ibanFromForm, codeFromForm)){
//            boolean isUserAlreadySecondaryAccountHolder = makeSureSecondaryAccountHolderAddedAndDeleteTokens(user, codeFromForm, ibanFromForm);
//            if (isUserAlreadySecondaryAccountHolder) {
//                String alreadyAccountHolder = "Je bent al Mederekeninghouder.";
//                setErrorMessageAndPrepareForOutput(modelAndView, alreadyAccountHolder);
//            }
//        } else {
//            String errorNietCorrect = "IBAN en/of code zijn niet correct of je bent niet gemachtigd door de rekeninghouder.";
//            setErrorMessageAndPrepareForOutput(modelAndView, errorNietCorrect);
//        }
//        return modelAndView;
//    }


//    private boolean makeSureSecondaryAccountHolderAddedAndDeleteTokens(Customer user, String codeFromForm, String ibanFromForm) {
//        List<BankAccountHolderToken> validTokens = bankAccountHolderTokenService.getValidTokens(user, ibanFromForm);
//        BankAccount bankAccountToAddCustomer = validTokens.get(0).getAccountToAdd();
//        boolean isUserAlreadySeconderyAccountHolder = bankAccountService.isCustomerSecondAccountHolder(user, bankAccountToAddCustomer);
//        if (!isUserAlreadySeconderyAccountHolder) {
//            bankAccountToAddCustomer.getSecondaryAccountHolders().add(user);
//        }
//        bankAccountHolderTokenService.deleteTokens(validTokens);
//        return isUserAlreadySeconderyAccountHolder;
//    }
}
