package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.OpenBusinessAccountForm;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.IbanGenerator;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

import static nl.dagobank.webapp.backingbeans.OpenBusinessAccountForm.sbiCodes;

@Controller
@SessionAttributes( "user" )
public class OpenBusinessAccountController {
    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    IbanGenerator ibanGenerator;
    @Autowired
    BankAccountService bankAccountService;

    //BusinessAccount businessAccount;

    public OpenBusinessAccountController() {
    }

    @GetMapping("openBusinessAccount")
    public ModelAndView openBusinessAccountHandler(Model model) {
        ModelAndView openBusinessAccountPage = new ModelAndView("openBusinessAccount");
        Customer customer = (Customer) model.getAttribute("user");
        openBusinessAccountPage.addObject("customerName", customer.getFullName());
        model.addAttribute("sbiCodes", sbiCodes);
        openBusinessAccountPage.addObject("openBusinessAccountForm", new OpenBusinessAccountForm());
        return openBusinessAccountPage;
    }

    @PostMapping("openBusinessAccount")
    public ModelAndView openBusinessAccountSuccessfulHandler(@ModelAttribute OpenBusinessAccountForm openBusinessAccountForm, Model model, BusinessAccount businessAccount) {
        if(!bankAccountService.isCompanyValid(openBusinessAccountForm.getKvkNumber())) {
            return showBusinessAccountOpenedSuccess(openBusinessAccountForm, model, businessAccount);
        } else {
            return showOpenAnotherAccount(openBusinessAccountForm, model);
        }
    }



    private  ModelAndView showBusinessAccountOpenedSuccess(@ModelAttribute OpenBusinessAccountForm openBusinessAccountForm, Model model, BusinessAccount businessAccount){
        ModelAndView businessAccountOpenened = new ModelAndView("openBusinessAccountSuccesful");
        Customer customer = (Customer) model.getAttribute("user");//FIXME: check how this works
        businessAccount.setAccountHolder(customer);
        businessAccount.setBusinessName(openBusinessAccountForm.getBusinessName());
        businessAccount.setKvkNumber(openBusinessAccountForm.getKvkNumber());
        businessAccount.setSbiCode(openBusinessAccountForm.getSbiCode());
        businessAccount.setAccountName(openBusinessAccountForm.getBankAccountName());
        businessAccount.setBalance(new BigDecimal("25"));
        Iban iban = ibanGenerator.createIban();
        businessAccount.setIban(iban.toString());
        bankAccountDao.save(businessAccount);
        return businessAccountOpenened;
    }

    private ModelAndView showOpenAnotherAccount(OpenBusinessAccountForm openBusinessAccountForm, Model model) {
    ModelAndView openAnotherBusinessAccount = new ModelAndView("openAnotherBusinessAccount");
    return openAnotherBusinessAccount;
    }








}
