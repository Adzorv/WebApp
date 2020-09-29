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
import java.util.List;

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

    @GetMapping( "open-zakelijke-rekening" )
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
        Gson gson = new Gson();
        String jsonBusiness = gson.toJson( businesses );

        mav.addObject( "businesses", businesses );
        mav.addObject( "businessesJson", jsonBusiness );
        return mav;

    }

    @PostMapping( "open-zakelijke-rekening" )
    public ModelAndView openAccountForExistingBusiness( Model model, @ModelAttribute OpenBusinessAccountForm openBusinessAccountForm ) {
        ModelAndView mav = new ModelAndView( "openBusinessAccountSuccessful" );
        return mav;

    }

    @GetMapping( "getAllBusinesses" )
    @ResponseBody
    public ArrayList<Business> getAllBusinesses( Model model ) {
        System.out.println( "In getAllBusiness getmapping " );
        ArrayList<Business> businesses = new ArrayList<>();
        Customer customer = (Customer) model.getAttribute( "user" );
        List<BusinessAccount> accounts = bankAccountService.findAllBusinessAccountsByCustomer( customer );
        for ( BusinessAccount account : accounts ) {
            Business business = new Business( account.getBusinessName(), account.getKvkNumber(), account.getSbiCode() );
            businesses.add( business );
        }
        return businesses;
    }

    @GetMapping( "openBusinessAccount" )
    public ModelAndView openBusinessAccountHandler( Model model ) {
        ModelAndView openBusinessAccountPage = new ModelAndView( "openBusinessAccount" );
        Customer customer = (Customer) model.getAttribute( "user" );
        openBusinessAccountPage.addObject( "customerName", customer.getFullName() );
        model.addAttribute( "sbiCodes", sbiCodes );
        openBusinessAccountPage.addObject( "openBusinessAccountForm", new OpenBusinessAccountForm() );
        return openBusinessAccountPage;
    }

    @PostMapping( "openBusinessAccount" )
    public ModelAndView openBusinessAccountSuccessfulHandler( @ModelAttribute OpenBusinessAccountForm openBusinessAccountForm, Model model, BusinessAccount businessAccount ) {
        if ( !bankAccountService.isCompanyValid( openBusinessAccountForm.getKvkNumber() ) ) {
            return showBusinessAccountOpenedSuccess( openBusinessAccountForm, model, businessAccount );
        } else {
            return showOpenAnotherAccount( openBusinessAccountForm, model );
        }
    }


    private ModelAndView showBusinessAccountOpenedSuccess( @ModelAttribute OpenBusinessAccountForm openBusinessAccountForm, Model model, BusinessAccount businessAccount ) {
        ModelAndView businessAccountOpenened = new ModelAndView( "openBusinessAccountSuccessful" );
        Customer customer = (Customer) model.getAttribute( "user" );//FIXME: check how this works
        businessAccount.setAccountHolder( customer );
        businessAccount.setBusinessName( openBusinessAccountForm.getBusinessName() );
        businessAccount.setKvkNumber( openBusinessAccountForm.getKvkNumber() );
        businessAccount.setSbiCode( openBusinessAccountForm.getSbiCode() );
        businessAccount.setAccountName( openBusinessAccountForm.getBankAccountName() );
        businessAccount.setBalance( new BigDecimal( "25" ) );
        Iban iban = ibanGenerator.createIban();
        businessAccount.setIban( iban.toString() );
        businessAccountDao.save( businessAccount );
        businessAccountOpenened.addObject( "bankaccount", businessAccount );
        return businessAccountOpenened;
    }

    private ModelAndView showOpenAnotherAccount( OpenBusinessAccountForm openBusinessAccountForm, Model model ) {
        ModelAndView openAnotherBusinessAccount = new ModelAndView( "openAnotherBusinessAccount" );
        return openAnotherBusinessAccount;
    }

    @PostMapping( "openAnotherBusinessAccount" )
    public ModelAndView openAnotherBusinessAccount( @RequestParam( "bankAccountName" ) String bankAccountName, Model model, BusinessAccount businessAccount ) {
        ModelAndView openBusinessAccountSuccessful = new ModelAndView( "openBusinessAccountSuccessful" );
        createAndSaveAnotherBusinessAccount( bankAccountName, model, businessAccount );
        openBusinessAccountSuccessful.addObject( "bankaccount", businessAccount );
        return openBusinessAccountSuccessful;
    }

    private BusinessAccount createAndSaveAnotherBusinessAccount( String bankAccountName, Model model, BusinessAccount businessAccount ) {
        Customer customer = (Customer) model.getAttribute( "user" );
        businessAccount = (BusinessAccount) model.getAttribute( "businessAccount" );
        businessAccount.setAccountHolder( customer );
        businessAccount.setAccountName( bankAccountName );
        businessAccount.setBalance( new BigDecimal( "25" ) );
        Iban iban = ibanGenerator.createIban();
        businessAccount.setIban( iban.toString() );
        businessAccount.getBusinessName();
        businessAccount.getKvkNumber();
        businessAccount.getSbiCode();

       /*
        businessAccount.setBusinessName(businessAccount.getBusinessName());//fixme: how to retrieve the information of the company
        businessAccount.setKvkNumber(businessAccount.getKvkNumber());
        businessAccount.setSbiCode(businessAccount.getSbiCode());*/
        businessAccountDao.save( businessAccount );
        return businessAccount;

    }


}
