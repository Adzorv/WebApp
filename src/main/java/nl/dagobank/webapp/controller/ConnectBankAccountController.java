package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ConnectBankAccountForm;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.BankAccountHolderTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class ConnectBankAccountController {

    @Autowired
    BankAccountHolderTokenService bankAccountHolderTokenService;



    @GetMapping("connectBankAccount")
    public String connectBankAccountLoaderHandler(Model model){
        model.addAttribute("text", "text voor connection page");
        return "connectBankAccount";
    }

    @PostMapping("connectBankAccount")
    public ModelAndView connectBankAccountHandler(@ModelAttribute ConnectBankAccountForm connectBankAccountForm, Model model){
        ModelAndView modelAndView = new ModelAndView("bankAccountConnectionSuccess");
        modelAndView.addObject("text", "text voor connectionSuccess pagina");
        Customer user = (Customer)model.getAttribute( "user" );
        String codeFromForm = connectBankAccountForm.getConnectionCode();
        String ibanFromForm = connectBankAccountForm.getIban();

        if (bankAccountHolderTokenService.existsValidToken(user, ibanFromForm, codeFromForm)){
            System.out.println("Yes we can connect!");
        } else {
            System.out.println("No we cant connect");
        }
        return modelAndView;
    }


}
