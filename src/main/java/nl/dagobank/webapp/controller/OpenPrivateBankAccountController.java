package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.IbanGenerator;
import org.iban4j.Iban;
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

    @Autowired
    IbanGenerator ibanGenerator;

    private final BigDecimal BANKACCOUNT_BEGINBALANCE_GIFT = new BigDecimal("25");

    @GetMapping("openPrivateBankAccount")
    public ModelAndView openBankAccountHandler(Model model) {
        ModelAndView modelAndView = new ModelAndView("openPrivateBankAccount");
        generateBankAccountNameAndPutInModel(model, modelAndView);
        return modelAndView;
    }

    private void generateBankAccountNameAndPutInModel(Model model, ModelAndView modelAndView) {
        Customer user = (Customer) model.getAttribute("user");
        int aantalRekeningenVanUser = bankAccountService.getNumberOfBankAccountsOfCustomer(user);
        String bankAccountName = user.getFirstName() + "'s rekening " + (aantalRekeningenVanUser+1);
        modelAndView.addObject("bankAccountName", bankAccountName);
    }


    @PostMapping("/openAndSaveBankAccount")
    public ModelAndView openBankAccountSuccessHandler(@RequestParam("bankAccountName") String bankAccountName, Model model, PrivateAccount privateAccount) {
        ModelAndView modelAndView = new ModelAndView("openPrivateBankAccountSuccess");
        createAndSavePrivateAccount(bankAccountName, model, privateAccount);
        modelAndView.addObject("bankaccount", privateAccount);
        return modelAndView;
    }

    private void createAndSavePrivateAccount(String bankAccountName, Model model, PrivateAccount privateAccount) {
        Customer user = (Customer) model.getAttribute("user");
        privateAccount.setAccountHolder(user);
        privateAccount.setAccountName(bankAccountName);
        privateAccount.setBalance(BANKACCOUNT_BEGINBALANCE_GIFT);
        Iban iban = ibanGenerator.createIban();
        privateAccount.setIban(iban.toString());
        bankAccountService.savePrivateAccount(privateAccount);
    }

}





