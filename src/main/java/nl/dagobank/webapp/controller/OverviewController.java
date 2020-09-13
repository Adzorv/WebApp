package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes( "user" )
public class OverviewController {

    @Autowired
    BankAccountService bankAccountService;


    @GetMapping("overview")
    public String overview(Model model) {
        Customer user = (Customer)model.getAttribute( "user" );
        List<BankAccount> bankAccountsOfUser = bankAccountService.getAllAccountsFromCustomer(user);
        model.addAttribute("bankAccounts", bankAccountsOfUser);
        return "overview";
    }
}
