package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.Business;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.PrivateAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.TestDataUserService;
import nl.dagobank.webapp.service.TransactionService;
import nl.dagobank.webapp.service.TransferService;
import nl.dagobank.webapp.util.generator.BusinessGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private BusinessAccountDao businessAccountDao;
    private TestDataUserService testDataUserService;
    private TransferService transactionService;


    @Autowired
    public SeedDatabaseController( CustomerDao customerDao, PrivateAccountDao privateAccountDao, BusinessAccountDao businessAccountDao, TestDataUserService testDataUserService, TransferService transactionService ) {
        this.customerDao = customerDao;
        this.privateAccountDao = privateAccountDao;
        this.businessAccountDao = businessAccountDao;
        this.testDataUserService = testDataUserService;
        this.transactionService = transactionService;

    }

    @GetMapping( "vuldatabase" )
    public ModelAndView fillDatabase( Model model ) {
        LOG.info( "vuldatabse controller reached" );
        testDataUserService.createAndSaveUsers();
        LOG.info("created standard users");
        testDataUserService.createAndSaveUsers(400);
        LOG.info( "finished creating users");
        LOG.info( "add Private Bank Accounts");
        giveUsersPrivateBankAccounts();
        LOG.info( "add Business Bank Accounts");
        giveUsersBusinessBankAccounts();
        LOG.info( "generating transactions");
        generateTransactions();
        return new ModelAndView( "homepage" );
    }

    public void giveUsersPrivateBankAccounts() {
        Iterator<Customer> allCustomers = customerDao.findAll().iterator();
        IbanIterator ibanIterator = new IbanIterator();
        // This method iterates over all customers
        // It finds all customers that do not have any private accounts
        // Then it adds as much private accounts as the customers last digit of its BSN
        while ( allCustomers.hasNext() ) {
            Customer customer = allCustomers.next();
            List<PrivateAccount> allPrivateAccounts = privateAccountDao.findAllByAccountHolder( customer );
            if ( allPrivateAccounts.size() == 0 ) {
                privateAccountDao.save(generateRandomPrivateAccount( customer, ibanIterator ));
                LOG.info( "PrivateAccount created for Customer " + customer.getId() );

/*                int bsn = customer.getPersonalDetails().getBsn();
                int lastNumberOfBsn = bsn % 10;
                if ( lastNumberOfBsn == 0 ) {
                    PrivateAccount privateAccount = generateRandomPrivateAccount( customer, ibanIterator );
                    privateAccountDao.save( privateAccount );
                    LOG.info( "1 PrivateAccounts created for Customer " + customer.getId() );
                } else {
                    for ( int i = 0 ; i < lastNumberOfBsn ; i++ ) {
                        PrivateAccount privateAccount = generateRandomPrivateAccount( customer, ibanIterator );
                        privateAccountDao.save( privateAccount );
                    }
                    LOG.info( lastNumberOfBsn + " PrivateAccounts created for Customer " + customer.getId() );
                }*/
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

    public void giveUsersBusinessBankAccounts() {
        Iterator<Customer> allCustomers = customerDao.findAll().iterator();
        IbanIterator ibanIterator = new IbanIterator( 7000000000L );
        BusinessGenerator businessGenerator = new BusinessGenerator( 11122233 );

        while ( allCustomers.hasNext() ) {
            Customer customer = allCustomers.next();
            if ( customer.getId() % 5 == 0 ) {
                int lastNumberOfBsn = customer.getPersonalDetails().getBsn() % 10;
                Business mainBusiness = businessGenerator.next();
                businessAccountDao.save(generateRandomBusinessAccount( customer, mainBusiness, ibanIterator ));
                /*for ( int i = 0 ; i < lastNumberOfBsn ; i++ ) {
                    if ( i % 3 == 0 ) {
                        BusinessAccount businessAccount = generateRandomBusinessAccount( customer, businessGenerator.next(), ibanIterator );
                        businessAccountDao.save( businessAccount );
                    } else {
                        BusinessAccount businessAccount = generateRandomBusinessAccount( customer, mainBusiness, ibanIterator );
                        businessAccountDao.save( businessAccount );
                    }
                }*/
                LOG.info( "BusinessAccount created for Customer " + customer.getId() );
            }
        }
    }

    private BusinessAccount generateRandomBusinessAccount( Customer customer, Business business, IbanIterator ibanIterator ) {
        BusinessAccount account = new BusinessAccount( business.getBusinessName(), business.getKvkNumber(), business.getSbiCode() );
        account.setAccountName( "zakelijke rekening" );
        account.setIban( ibanIterator.next().toString() );
        account.setBalance( new BigDecimal( ThreadLocalRandom.current().nextInt( 25, 2000 + 1 ) ) );
        account.setAccountHolder( customer );
        return account;
    }

    private void generateTransactions() {
        List<BusinessAccount> allAccounts = businessAccountDao.findAll();
        int ceiling = allAccounts.size();
        for ( BusinessAccount account : allAccounts ) {
            int randomAmountOfTransactions = ThreadLocalRandom.current().nextInt( 1, 10 );
            for ( int i = 0 ; i < randomAmountOfTransactions ; i++ ) {
                int randomId = ThreadLocalRandom.current().nextInt( 0, ceiling );
                BigDecimal randomAmmount = new BigDecimal( ThreadLocalRandom.current().nextInt( 10, 10000 ) );
                if ( allAccounts.get(randomId).getId() != account.getId() ) {
                    transactionService.performTransaction( account, allAccounts.get( randomId ), randomAmmount, "transactie" );
                }
            }
        }
    }
}
