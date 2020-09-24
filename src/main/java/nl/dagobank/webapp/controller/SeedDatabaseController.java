package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.PrivateAccountDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class SeedDatabaseController {

    private static final Logger LOG = LogManager.getLogger( LoginController.class );

    private CustomerDao customerDao;
    private PrivateAccountDao privateAccountDao;


    @Autowired
    public SeedDatabaseController( CustomerDao customerDao, PrivateAccountDao privateAccountDao ) {
        this.customerDao = customerDao;
        this.privateAccountDao = privateAccountDao;
    }

    @GetMapping( "vuldatabase" )
    public ModelAndView fillDatabase( Model model ) {
        LOG.info( "vuldatabse controller reached" );
        giveUsersBankAccounts();
        return new ModelAndView( "homepage" );
    }

    public void giveUsersBankAccounts() {
        Iterator<Customer> allCustomers = customerDao.findAll().iterator();
        IbanIterator ibanIterator = new IbanIterator();
        // This method iterates over all customers
        // It finds all customers that do not have any private accounts
        // Then it adds as much private accounts as the customers last digit of its BSN
        while ( allCustomers.hasNext() ) {
            Customer customer = allCustomers.next();
            List<PrivateAccount> allPrivateAccounts = privateAccountDao.findAllByAccountHolder( customer );
            if ( allPrivateAccounts.size() == 0 ) {
                int bsn = customer.getPersonalDetails().getBsn();
                int lastNumberOfBsn = bsn % 10;
                if ( lastNumberOfBsn == 0 ) {
                    PrivateAccount privateAccount = generateRandomPrivateAccount( customer, ibanIterator );
                    privateAccountDao.save( privateAccount );
                    LOG.info( "1 bankaccount created for Customer " + customer.getId() );
                } else {
                    for ( int i = 0 ; i < lastNumberOfBsn ; i++ ) {
                        PrivateAccount privateAccount = generateRandomPrivateAccount( customer, ibanIterator );
                        privateAccountDao.save( privateAccount );
                    }
                    LOG.info( lastNumberOfBsn + " bankaccounts created for Customer " + customer.getId() );
                }
            }
        }
    }


    public PrivateAccount generateRandomPrivateAccount( Customer customer, IbanIterator ibanIterator ) {
        PrivateAccount account = new PrivateAccount();
        int randomNum = ThreadLocalRandom.current().nextInt( 25, 2000 + 1 );
        BigDecimal balance = new BigDecimal( randomNum );

        account.setBalance( balance );
        account.setAccountName( "betaalrekening" );
        account.setIban( ibanIterator.next().toString() );
        account.setAccountHolder( customer );

        return account;
    }

}
