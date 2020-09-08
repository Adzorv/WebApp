package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.BusinessAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BusinessAccountDao businessAccountDao;
    @Autowired
    private LoginValidatorCustomer loginValidator;

    public boolean isRegisteredUserName( String userName ) {
        return customerDao.existsByUserName( userName );
    }

    public Customer getCustomerByUserName( String userName ) {
        return customerDao.findByUserName( userName ).get();
    }

    public boolean checkIfBSNIsInDB( int bsn ) {
        return customerDao.findByBsn( bsn ) != null;
    }

    public boolean checkIfBSNIsCorrect( int inputBSN ) {
        if ( inputBSN <= 9999999 || inputBSN > 999999999 ) {
            return false;
        }
        int bsnToCheck = -1 * inputBSN % 10;
        for ( int multiplier = 2 ; inputBSN > 0 ; multiplier++ ) {
            int val = ( inputBSN /= 10 ) % 10;
            bsnToCheck += multiplier * val;
        }
        return bsnToCheck != 0 && bsnToCheck % 11 == 0;
    }

    public void saveCustomer( Customer customer ) {
        customerDao.save( customer );
    }

    public LoginValidatorCustomer validateCredentials( LoginForm loginForm ) {
        loginValidator.validateCredentials( loginForm );
        return loginValidator;
    }

    public boolean isBSNValid( int bsn ) {
        return ( checkIfBSNIsCorrect( bsn ) && !checkIfBSNIsInDB( bsn ) );
    }

    public List<Map.Entry<Customer, BigDecimal>> getAllBusinessCustomers() {
        Iterator<Customer> all = getAllCustomersIterator();
        BigDecimal totalBalance = new BigDecimal( 0 );
        Map<Customer, BigDecimal> result = new HashMap<>();

        while ( all.hasNext() ) {
            Customer customer = all.next();
            List<BusinessAccount> businessAccounts = businessAccountDao.findAllByAccountHolder( customer );
            if ( !businessAccounts.isEmpty() ) {
                for ( BusinessAccount ba : businessAccounts ) {
                    totalBalance = totalBalance.add( ba.getBalance() );
                }
                result.put( customer, totalBalance );
            }
            System.out.println();
            System.out.println( MapUtil.entriesSortedByValues( result ) );
        }
        return MapUtil.entriesSortedByValues( result ).subList( 0, 10 ); //TODO testen
    }

    private Iterator<Customer> getAllCustomersIterator() {
        return customerDao.findAll().iterator();
    }


}
