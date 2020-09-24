package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.util.BsnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LoginValidatorCustomer loginValidator;

    public boolean isRegisteredUserName( String userName ) {
        return customerDao.existsByInlogCredentialsUserName( userName );
    }

    public Customer getCustomerByUserName( String userName ) {
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
        return BsnUtil.isCorrect( inputBSN );
    }

    public void saveCustomer( Customer customer ) {
        customerDao.save( customer );
    }

    public Customer saveCustomerWithReturn(Customer customer){
        return customerDao.save( customer );
    }

    public LoginValidatorCustomer validateCredentials( LoginForm loginForm ) {
        loginValidator.validateCredentials( loginForm );
        return loginValidator;
    }
}
