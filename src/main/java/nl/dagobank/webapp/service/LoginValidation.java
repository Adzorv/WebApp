package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.LoginAttemptDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.LoginAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginValidation {

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
    private static final int MAXIMUM_TRIES = 3, TIME0UT = 2;


    public void validateCredentials( LoginForm loginForm ) {
        this.loginForm = loginForm;
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
        loginAttempt = getOrCreateLoginAttempt();
        checkResetBlockedCustomer();
        if ( loginAttempt.getFailedAttempts() > MAXIMUM_TRIES - 1 ) return blockCustomer();
        if ( customer != null ) return passwordCheck();
        return false;
    }

    private void checkResetBlockedCustomer() {
        if ( loginAttempt.getBlockedUntil() != null ) {
            if ( loginAttempt.getBlockedUntil().isBefore( LocalDateTime.now() ) ) {
                loginAttempt.resetFailedAttempts();
                loginAttempt.setBlockedUntil( null );
            }
        }
    }

    private boolean blockCustomer() {
        LocalDateTime blockedUntil = loginAttempt.getTimeAtLastLoginAttempt().plusMinutes( TIME0UT );
        loginAttempt.setBlockedUntil( blockedUntil );
        loginForm.setLoginAttemptsError( String.format( "3x fout ingelogd! Je mag pas weer inloggen om %s", blockedUntil.toLocalTime().toString() ) );
        loginAttemptDao.save( loginAttempt );
        return false;
    }

    private boolean passwordCheck() {
        if ( customer.getPassword().equals( loginForm.getPassword() ) ) {
            logMessage = SUCCESS + " | " + customer;
//            loginAttempt.resetFailedAttempts();
//            loginAttemptDao.save( loginAttempt );
            loginAttemptDao.delete( loginAttempt );
            return true;
        } else {
            logMessage = WRONGPASSWORD + " | " + loginForm.getPassword();
            loginForm.setPasswordError( LOGINERROR_PASSWORD );
            loginForm.setLoginAttemptsError( String.format( "Nog %d inlogpogingen over..", MAXIMUM_TRIES - loginAttempt.getFailedAttempts() ) );
            loginAttempt.incrementFailedAttempts();
            loginAttempt.setTimeAtLastLoginAttempt( LocalDateTime.now() );
            loginAttemptDao.save( loginAttempt );
            return false;
        }
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
        return loginAttemptDao.findByCustomer( customer ).orElseGet( () -> new LoginAttempt( customer ) );
    }

}
