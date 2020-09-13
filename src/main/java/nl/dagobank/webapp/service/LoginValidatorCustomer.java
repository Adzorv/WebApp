package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.LoginAttemptDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.LoginAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class LoginValidatorCustomer {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LoginAttemptDao loginAttemptDao;

    private LoginAttempt loginAttempt;
    private LoginForm loginForm;
    private Customer customer;
    private boolean userValidated, passwordValidated;
    private String logMessage;
    private static final String WRONGUSER = "Wrong username",
            WRONGPASSWORD = "Wrong password",
            SUCCESS = "Gebruiker ingelogd",
            LOGINERROR_USERNAME = "Gebruikersnaam bestaat niet",
            LOGINERROR_PASSWORD = "Verkeerd wachtwoord";
    private static final int MAXIMUM_TRIES = 3, TIME0UT = 1;


    public void validateCredentials( LoginForm loginForm ) {
        this.loginForm = loginForm;
        this.userValidated = validateUserName();
        this.passwordValidated = this.userValidated && validatePassword();
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
        loginAttempt = getOrCreateLoginAttempt();
        checkResetBlockedCustomer();
        if ( loginAttempt.getFailedAttempts() > MAXIMUM_TRIES - 1 ) {
            blockCustomer();
            return false;
        }
        return passwordCheck();
    }

    private void checkResetBlockedCustomer() {
        if ( loginAttempt.getBlockedUntil() != null ) {
            if ( loginAttempt.getBlockedUntil().isBefore( LocalDateTime.now() ) ) {
                loginAttempt.resetFailedAttempts();
                loginAttempt.setBlockedUntil( null );
            }
        }
    }

    private void blockCustomer() {
        LocalDateTime blockedUntil = LocalDateTime.now().plusMinutes( TIME0UT );
        loginAttempt.setBlockedUntil( blockedUntil );
        loginForm.setLoginAttemptsError( String.format( "3x fout ingelogd! Je mag pas weer inloggen om %s", blockedUntil.toLocalTime().format( DateTimeFormatter.ofPattern("HH:mm:ss") ) ) );
        loginAttemptDao.save( loginAttempt );
    }

    private boolean passwordCheck() {
        if ( customer != null ) {
            if ( customer.getUserInlogCredentials().getPassword().equals( loginForm.getPassword() ) ) {
                logMessage = SUCCESS + " | " + customer;
                loginAttemptDao.delete( loginAttempt );
                return true;
            } else {
                logMessage = WRONGPASSWORD + " | " + loginForm.getPassword();
                loginForm.setPasswordError( LOGINERROR_PASSWORD );
                loginForm.setLoginAttemptsError( String.format( "Nog %d inlogpogingen over..", MAXIMUM_TRIES - loginAttempt.getFailedAttempts() ) );
                loginAttempt.incrementFailedAttempts();
                loginAttemptDao.save( loginAttempt );
                return false;
            }
        }
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

    private LoginAttempt getOrCreateLoginAttempt() {
        return loginAttemptDao.findByCustomer(customer).orElseGet( () -> new LoginAttempt(customer) );
    }

}
