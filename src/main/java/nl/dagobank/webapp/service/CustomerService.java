package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return customerDao.existsByInlogCredentialsUserName( userName );
    }

    public Customer getCustomerByUserName(String userName ) {
        Optional<Customer> optionalCustomer = customerDao.findByInlogCredentialsUserName( userName );
        return optionalCustomer.orElse( null );
    }
    public boolean isBSNValid( int bsn ) {
        return ( checkIfBSNIsCorrect( bsn ) && !checkIfBSNIsInDB( bsn ) );
    }

    public boolean checkIfBSNIsInDB( int bsn ) {
        return customerDao.findByPersonalDetailsBsn( bsn ) != null;
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

    public void saveCustomer( Customer customer) {
        customerDao.save(customer);
    }

    public LoginValidatorCustomer validateCredentials( LoginForm loginForm ) {
        loginValidator.validateCredentials( loginForm );
        return loginValidator;
    }
}
