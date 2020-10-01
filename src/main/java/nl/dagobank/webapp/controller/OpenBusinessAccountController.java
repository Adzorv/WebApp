package nl.dagobank.webapp.controller;


import nl.dagobank.webapp.backingbeans.AccountInfo;
import nl.dagobank.webapp.backingbeans.Business;
import nl.dagobank.webapp.backingbeans.OpenBusinessAccountForm;
import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.IbanGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes(BaseController.USER_SESSION_ATTR )
public class OpenBusinessAccountController extends BaseController {

    private IbanGenerator ibanGenerator;
    private BankAccountService bankAccountService;
    public static final String BUSINESSNAME_EXISTS_ERROR = "Dit KVK nummer is al geregistreerd onder een andere naam.",
    SECTOR_EXISTS_ERROR = "Dit KVK nummer is al geregistreerd onder een andere sector.";


    @Autowired
    public OpenBusinessAccountController( IbanGenerator ibanGenerator, BankAccountService bankAccountService ) {
        this.ibanGenerator = ibanGenerator;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping( "openBusinessAccount" )
    public ModelAndView openBusinessAccountLandingPage( Model model, @ModelAttribute OpenBusinessAccountForm openBusinessAccountForm ) {
        ModelAndView mav = new ModelAndView();
        if ( model.getAttribute( USER_SESSION_ATTR ) != null ) {
            mav.setViewName( "openBusinessAccount" );
        } else {
            mav.setViewName( NO_ACCESS_VIEW );
        }
        return mav;
    }

    @GetMapping( "getAllBusinesses" )
    @ResponseBody
    public Set<Business> getAllBusinesses( Model model ) {
        Customer customer = (Customer) model.getAttribute( USER_SESSION_ATTR );
        return getAllBusinessFor( customer );
    }

    @PostMapping( "openBusinessAccount" )
    public ModelAndView openBusinessAccountSuccessfulHandler( @ModelAttribute OpenBusinessAccountForm openBusinessAccountForm, Model model ) {
        Customer customer = (Customer) model.getAttribute( USER_SESSION_ATTR );
        Set<Business> existingBusinesses = getAllBusinessFor( customer );
        Business newBusiness = new Business( openBusinessAccountForm.getBusinessName(), openBusinessAccountForm.getKvkNumber(), openBusinessAccountForm.getSbiCode() );
        for ( Business business : existingBusinesses ) {
            if ( business.getKvkNumber() == newBusiness.getKvkNumber() ) {
                if ( !business.getBusinessName().equals( newBusiness.getBusinessName() ) ) {
                    openBusinessAccountForm.setError( BUSINESSNAME_EXISTS_ERROR );
                    return new ModelAndView( "openBusinessAccount" );
                } else if ( !business.getSbiCode().equals( newBusiness.getSbiCode() ) ) {
                    openBusinessAccountForm.setError( SECTOR_EXISTS_ERROR );
                    return new ModelAndView( "openBusinessAccount" );
                }
            }
        }
        BusinessAccount businessAccount = saveAndCreateBusinessAccount( customer, openBusinessAccountForm );
        return new ModelAndView(  "openBusinessAccountSuccessful" ).addObject( "bankaccount", businessAccount );
    }

    private BusinessAccount saveAndCreateBusinessAccount( Customer customer, OpenBusinessAccountForm openBusinessAccountForm ) {
        AccountInfo accountInfo = new AccountInfo( openBusinessAccountForm.getBankAccountName(), ibanGenerator.createIban().toString() );
        Business business = new Business(
                openBusinessAccountForm.getBusinessName(),
                openBusinessAccountForm.getKvkNumber(),
                openBusinessAccountForm.getSbiCode()
        );
        BusinessAccount businessAccount = new BusinessAccount( accountInfo, customer, business );
        bankAccountService.saveBusinessAccount( businessAccount );
        return businessAccount;
    }

    private Set<Business> getAllBusinessFor( Customer customer ) {
        Set<Business> businesses = new HashSet<>();
        List<BusinessAccount> accounts = bankAccountService.findAllBusinessAccountsByCustomer( customer );
        for ( BusinessAccount account : accounts ) {
            Business business = new Business( account.getBusinessName(), account.getKvkNumber(), account.getSbiCode() );
            businesses.add( business );
        }
        return businesses;
    }
}
