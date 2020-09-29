package nl.dagobank.webapp.controller;

import com.google.gson.Gson;
import nl.dagobank.webapp.backingbeans.Business;
import nl.dagobank.webapp.backingbeans.OpenBusinessAccountForm;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.BusinessAccountDao;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nl.dagobank.webapp.backingbeans.OpenBusinessAccountForm.sbiCodes;

@Controller
@SessionAttributes( { "user", "businessAccount" } )
public class OpenBusinessAccountController {

    private BankAccountDao bankAccountDao;
    private IbanGenerator ibanGenerator;
    private BankAccountService bankAccountService;
    private BusinessAccountDao businessAccountDao;

    public OpenBusinessAccountController( BankAccountDao bankAccountDao, IbanGenerator ibanGenerator, BankAccountService bankAccountService, BusinessAccountDao businessAccountDao ) {
        this.bankAccountDao = bankAccountDao;
        this.ibanGenerator = ibanGenerator;
        this.bankAccountService = bankAccountService;
        this.businessAccountDao = businessAccountDao;
    }

    @GetMapping( "openBusinessAccount" )
    public ModelAndView openBusinessAccountLandingPage( Model model, @ModelAttribute OpenBusinessAccountForm openBusinessAccountForm ) {
        ModelAndView mav = new ModelAndView();
        if ( model.getAttribute( "user" ) != null ) {
            mav.setViewName( "openBusinessAccountExistingBusiness" );
        } else {
            mav.setViewName( "geenToegang" );
        }
        Customer customer = (Customer) model.getAttribute( "user" );
        List<Business> businesses = new ArrayList<>();
        List<BusinessAccount> accounts = bankAccountService.findAllBusinessAccountsByCustomer( customer );
        for ( BusinessAccount account : accounts ) {
            Business business = new Business( account.getBusinessName(), account.getKvkNumber(), account.getSbiCode() );
            businesses.add( business );
        }
        return mav;
    }

    @GetMapping( "getAllBusinesses" )
    @ResponseBody
    public Set<Business> getAllBusinesses( Model model ) {
        Set<Business> businesses = new HashSet<>();
        Customer customer = (Customer) model.getAttribute( "user" );
        return getAllBusinessFor( customer );
    }

    @PostMapping( "openBusinessAccount" )
    public ModelAndView openBusinessAccountSuccessfulHandler( @ModelAttribute OpenBusinessAccountForm openBusinessAccountForm, Model model ) {
        Customer customer = (Customer) model.getAttribute( "user" );
        Set<Business> existingBusinesses = getAllBusinessFor( customer );
        Business newBusiness = new Business( openBusinessAccountForm.getBusinessName(), openBusinessAccountForm.getKvkNumber(), openBusinessAccountForm.getSbiCode() );
        for ( Business business : existingBusinesses ) {
            if ( business.getKvkNumber() == newBusiness.getKvkNumber() ) {
                if ( !business.getBusinessName().equals( newBusiness.getBusinessName() ) ) {
                    openBusinessAccountForm.setError( "Dit KVK nummer is al geregistreerd onder een andere naam." );
                    return new ModelAndView( "openBusinessAccountExistingBusiness" );
                } else if ( !business.getSbiCode().equals( newBusiness.getSbiCode() ) ) {
                    openBusinessAccountForm.setError( "Dit KVK nummer is al geregistreerd onder een andere sector." );
                    return new ModelAndView( "openBusinessAccountExistingBusiness" );
                }
            }
        }
        BusinessAccount businessAccount = saveBusinessAccount( customer, openBusinessAccountForm );
        return new ModelAndView(  "openBusinessAccountSuccessful" ).addObject( "bankaccount", businessAccount );
    }

/*    private ModelAndView checkBusinessNameAndSbiCode( Business currentBusiness, Business newBusiness, OpenBusinessAccountForm openBusinessAccountForm ) {
        if ( !currentBusiness.getBusinessName().equals( newBusiness.getBusinessName() ) ) {
            System.out.println("hiero 2");
            openBusinessAccountForm.setError( "Dit KVK nummer is al geregistreerd onder een andere naam." );
            return new ModelAndView( "openBusinessAccountExistingBusiness" );
        } else if ( !currentBusiness.getSbiCode().equals( newBusiness.getSbiCode() ) ) {
            openBusinessAccountForm.setError( "Dit KVK nummer is al geregistreerd onder een andere sector." );
            return new ModelAndView( "openBusinessAccountExistingBusiness" );
        }
    }*/

    private BusinessAccount saveBusinessAccount( Customer customer, OpenBusinessAccountForm openBusinessAccountForm ) {
        BusinessAccount businessAccount = new BusinessAccount();
        businessAccount.setAccountHolder( customer );
        businessAccount.setBusinessName( openBusinessAccountForm.getBusinessName() );
        businessAccount.setKvkNumber( openBusinessAccountForm.getKvkNumber() );
        businessAccount.setSbiCode( openBusinessAccountForm.getSbiCode() );
        businessAccount.setAccountName( openBusinessAccountForm.getBankAccountName() );
        businessAccount.setBalance( new BigDecimal( "25" ) );
        businessAccount.setIban( ibanGenerator.createIban().toString() );
        businessAccountDao.save( businessAccount );
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
