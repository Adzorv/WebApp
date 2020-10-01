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

    private CustomerDao customerDao;
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

    @Autowired
    public LoginValidatorCustomer( CustomerDao customerDao, LoginAttemptDao loginAttemptDao ) {
        this.customerDao = customerDao;
        this.loginAttemptDao = loginAttemptDao;
    }

    public void validateCredentials( LoginForm loginForm ) {
        this.loginForm = loginForm;
        this.userValidated = validateUserName();
        this.passwordValidated = this.userValidated && validatePassword();
    }

    private boolean validateUserName() {
        Optional customerOptional = customerDao.findByInlogCredentialsUserName( loginForm.getUsername() );
        if ( customerOptional.isPresent() ) {
            customer = (Customer) customerOptional.get();
            return true;
        }
        setLogMessage( WRONGUSER + " | " + loginForm.getUsername() );
        loginForm.setUsernameError( LOGINERROR_USERNAME );
        return false;
    }

    private boolean validatePassword() {
        getOrCreateLoginAttemptObject();
        unBlockUserIfNoLongerBlocked();

        if ( loginAttempt.isBlocked() ) {
            handleBlockedUser();
            return false;
        }
        if ( customer.getInlogCredentials().getPassword().equals( loginForm.getPassword() ) ) {
            handleCorrectPasword();
            return true;
        }

        handleWrongPassword();

        return false;

    }

    private void unBlockUserIfNoLongerBlocked() {
        if ( userIsNoLongerBlocked() ) {
            loginAttempt.resetFailedAttempts();
            loginAttempt.setBlockedUntil( null );
        }
    }

    private void handleBlockedUser() {
        loginForm.setLoginAttemptsError( String.format( "3x fout ingelogd! Je mag pas weer inloggen om %s", loginAttempt.getBlockedUntil().toLocalTime().format( DateTimeFormatter.ofPattern( "HH:mm:ss" ) ) ) );
    }

    private void handleCorrectPasword() {
        setLogMessage( SUCCESS + " | " + customer );
        loginAttemptDao.delete( loginAttempt );
    }

    private void handleWrongPassword() {
        setLogMessage( WRONGPASSWORD + " | " + loginForm.getPassword() );
        loginForm.setPasswordError( LOGINERROR_PASSWORD );
        loginForm.setLoginAttemptsError( String.format( "Nog %d inlogpogingen over..", MAXIMUM_TRIES - loginAttempt.getFailedAttempts() ) );
        loginAttempt.incrementFailedAttempts();
        loginAttemptDao.save( loginAttempt );

        if ( userHasExceededTries() ) {
            blockCustomer();
        }
    }

    private boolean userIsNoLongerBlocked() {
        return loginAttempt.getBlockedUntil() != null && loginAttempt.getBlockedUntil().isBefore( LocalDateTime.now() );
    }

    private boolean userHasExceededTries() {
        return loginAttempt.getFailedAttempts() > MAXIMUM_TRIES;
    }

    private void blockCustomer() {
        LocalDateTime blockedUntil = LocalDateTime.now().plusMinutes( TIME0UT );
        loginAttempt.setBlockedUntil( blockedUntil );
        loginForm.setLoginAttemptsError( String.format( "3x fout ingelogd! Je mag pas weer inloggen om %s", blockedUntil.toLocalTime().format( DateTimeFormatter.ofPattern( "HH:mm:ss" ) ) ) );
        loginAttemptDao.save( loginAttempt );
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

    public void setLogMessage( String logMessage ) {
        this.logMessage = logMessage;
    }

    public Customer getCustomer() {
        return customer;
    }

    private void getOrCreateLoginAttemptObject() {
        loginAttempt = loginAttemptDao.findByCustomer( customer ).orElseGet( () -> new LoginAttempt( customer ) );

    }

}
