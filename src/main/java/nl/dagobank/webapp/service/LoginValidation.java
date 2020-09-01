package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.LoginAttemptDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.LoginAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginValidation {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    LoginAttemptDao loginAttemptDao;
    private Customer customer;
    private boolean userValidated, passwordValidated;
    private String logMessage;
    private static final String WRONGUSER = "Wrong username",
            WRONGPASSWORD = "Wrong password",
            SUCCESS = "Gebruiker ingelogd",
            LOGINERROR_USERNAME = "Gebruikersnaam bestaat niet",
            LOGINERROR_PASSWORD = "Verkeerd wachtwoord";


    public void validateCredentials( LoginForm loginForm ) {
        this.userValidated = validateUserName( loginForm );
        if ( this.userValidated ) {
            this.passwordValidated = validatePassword( loginForm );
        } else {
            this.passwordValidated = false;
        }
    }

    private boolean validateUserName( LoginForm loginForm ) {
        Optional customerOptional = customerDao.findByUserName( loginForm.getUsername() );
        if ( customerOptional.isPresent() ) {
            customer = (Customer) customerOptional.get();
            return true;
        }
        logMessage = WRONGUSER + " | " + loginForm.getUsername();
        loginForm.setUsernameError( LOGINERROR_USERNAME );
        return false;
    }

    private boolean validatePassword( LoginForm loginForm ) {
        if ( customer != null ) {
            if ( customer.getPassword().equals( loginForm.getPassword() ) ) {
                logMessage = SUCCESS + " | " + customer;
                return true;
            }
        }
        logMessage = WRONGPASSWORD + " | " + loginForm.getPassword();
        loginForm.setPasswordError( LOGINERROR_PASSWORD );
        LoginAttempt la = getOrCreateLoginAttempt( customer );
        loginAttemptDao.save( la );
        System.out.println( la );

        return false;
    }


    public boolean isUserValidated() {
        return userValidated;
    }

    public boolean isPasswordValidated() {
        return passwordValidated;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public Customer getCustomer() {
        return customer;
    }

    private LoginAttempt getOrCreateLoginAttempt( Customer customer ) {
        Optional<LoginAttempt> loginAttemptOptional = loginAttemptDao.findByCustomer( customer );
        if ( loginAttemptOptional.isPresent() ) {
            LoginAttempt loginAttempt = loginAttemptOptional.get();
            loginAttempt.incrementFailedAttempts();
            return loginAttempt;
        } else {
            return new LoginAttempt( customer );
        }
//            return loginAttemptOptional.orElseGet( () -> new LoginAttempt( customer ) );
    }

}
