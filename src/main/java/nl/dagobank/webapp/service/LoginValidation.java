package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginValidation {

    private LoginForm loginForm;
    private CustomerDao customerDao;
    private Customer customer;
    private boolean userValidated, passwordValidated;
    private String logMessage;
    private static final String WRONGUSER = "Wrong username",
            WRONGPASSWORD = "Wrong password",
            SUCCESS = "Gebruiker ingelogd",
            LOGINERROR_USERNAME = "Gebruikersnaam bestaat niet",
            LOGINERROR_PASSWORD = "Verkeerd wachtwoord";

    @Autowired
    public LoginValidation( CustomerDao customerDao ) {
        this.customerDao = customerDao;
    }

    public void setLoginForm( LoginForm loginForm ) {
        this.loginForm = loginForm;
    }

    public void validateCredentials() {
        this.userValidated = validateUserName();
        if ( this.userValidated ) {
            this.passwordValidated = validatePassword();
        } else {
            this.passwordValidated = false;
        }
    }

    private boolean validateUserName() {
        Optional customerOptional = customerDao.findByUserName( loginForm.getUsername() );
        if ( customerOptional.isPresent() ) {
            customer = (Customer) customerOptional.get();
            return true;
        }
        logMessage = WRONGUSER + " | " + loginForm.getUsername();
        loginForm.setUsernameError( LOGINERROR_USERNAME );
        return false;
    }

    private boolean validatePassword() {
        if ( customer != null ) {
            if ( customer.getPassword().equals( loginForm.getPassword() ) ) {
                logMessage = SUCCESS + customer;
                return true;
            }
        }
        logMessage = WRONGPASSWORD + " | " + loginForm.getPassword();
        loginForm.setPasswordError( LOGINERROR_PASSWORD );
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

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
