package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LoginValidation loginValidation;

    public boolean isRegisteredUserName(String userName){
        return customerDao.existsByUserName(userName);
    }

    public boolean checkIfBSNIsInDB( int bsn ) {
        return customerDao.findByBsn( bsn ) != null;
    }

    public boolean checkIfBSNIsCorrect( int inputBSN ) {
        if ( inputBSN <= 9999999 || inputBSN > 999999999 ) {
            return false;
        }
        int bsnToCheck = -1 * inputBSN % 10;
        for (int multiplier = 2; inputBSN > 0; multiplier++) {
            int val = ( inputBSN /= 10 ) % 10;
            bsnToCheck += multiplier * val;
        }
        return bsnToCheck != 0 && bsnToCheck % 11 == 0;
    }

    public void saveCustomer( Customer customer ) {
        customerDao.save( customer );
    }

    public LoginValidation validateCredentials( LoginForm loginForm ) {
        loginValidation.validateCredentials( loginForm );
        return loginValidation;
    }

    public boolean isBSNValid( int bsn ) {
        return ( checkIfBSNIsCorrect( bsn ) && !checkIfBSNIsInDB( bsn ) );
    }
}
