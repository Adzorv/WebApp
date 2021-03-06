package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.AjaxIbanracerBalanceResponse;
import nl.dagobank.webapp.backingbeans.AjaxIbanracerResponse;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.domain.User;
import nl.dagobank.webapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@SessionAttributes( "user" )
public class IbanRacerController {

    BankAccountService bankAccountService;

    @Autowired
    public IbanRacerController( BankAccountService bankAccountService ) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping( "/ibanracer" )
    public String startRacer( Model model ) {
        return "ibanracer";
    }

    @PostMapping( "getBankAccounts" )
    @ResponseBody
    public AjaxIbanracerResponse getBankAccounts( Model model ) {
        AjaxIbanracerResponse response = new AjaxIbanracerResponse();
        Customer customer = (Customer) model.getAttribute( "user" );
        if ( customer != null ) {
            List<BankAccount> bankAccounts = bankAccountService.getAllAccountsFromCustomer( customer );
            response.setBankAccounts( bankAccounts );
        }
        return response;
    }

    @GetMapping( "getBankAccountBalance" )
    @ResponseBody
    public AjaxIbanracerBalanceResponse getBalance( @RequestParam String id ) {
        AjaxIbanracerBalanceResponse response = new AjaxIbanracerBalanceResponse();
        response.setBalance( bankAccountService.getBankAccountById( Integer.valueOf( id ) ).getBalance() );
        return response;
    }

    @PostMapping ( "increaseBalance" )
    @ResponseBody
    public BankAccount increaseBalance( @RequestBody PrivateAccount bankAccount ) {
        BankAccount accountFromDB = bankAccountService.getBankAccountById( bankAccount.getId() );
        accountFromDB.setBalance( accountFromDB.getBalance().add( new BigDecimal( 1 ) ) );
        bankAccountService.savePrivateAccount( (PrivateAccount)accountFromDB );
        return accountFromDB;
    }

}
