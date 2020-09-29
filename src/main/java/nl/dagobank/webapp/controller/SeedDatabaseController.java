package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.Business;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.PrivateAccountDao;
import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.TestDataService;
import nl.dagobank.webapp.service.TransferService;
import nl.dagobank.webapp.util.generator.BusinessGenerator;
import nl.dagobank.webapp.util.generator.IbanIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class SeedDatabaseController {

    private static final int DEFAULT_AMOUNT_OF_USERS_TO_GENERATE = 100;

    private static final Logger LOG = LogManager.getLogger( LoginController.class );

    private TestDataService testDataService;


    @Autowired
    public SeedDatabaseController(TestDataService testDataService ) {
        this.testDataService = testDataService;
    }

    @GetMapping( "vuldatabase" )
    public ModelAndView fillDatabase(@RequestParam(required = false) Integer amount, Model model) {
        LOG.info( "vuldatabse controller reached" );
        testDataService.createStandardTestUsers();
        LOG.info("created standard users");
        System.out.println("amount");
        System.out.println(amount);
        if ( amount != null && amount.intValue() != 0 ) {
            testDataService.createAndSaveUsers(amount);
        } else {
            testDataService.createAndSaveUsers(DEFAULT_AMOUNT_OF_USERS_TO_GENERATE);
        }
        LOG.info( "finished creating users");
        LOG.info( "add Private Bank Accounts");
        testDataService.giveUsersPrivateBankAccounts();
        LOG.info( "add Business Bank Accounts");
        testDataService.giveUsersBusinessBankAccounts();
        LOG.info( "generating transactions");
        testDataService.generateTransactions();
        LOG.info("finished generating test data");
        LOG.info( "finished creating transactions");
        return new ModelAndView( "homepage" );
    }

    @GetMapping( "delete")
    public ModelAndView deleteAllUserDataFromDatabase(){
        LOG.info(" starting delede data");
        testDataService.deleteAllUsersData();
        LOG.info ( "finished data deletion");
        return new ModelAndView( "homepage" );
    }
}
