package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.service.TestDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        LOG.info( "finished creating transactions");
        LOG.info( "adding secondary account holders");
        testDataService.addRandomSecondaryBankAccountHolders();
        LOG.info("finished generating test data");
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
