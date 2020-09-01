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
        LoginAttempt loginAttempt = getOrCreateLoginAttempt( customer );

        if ( loginAttempt.getBlockedUntil() != null && loginAttempt.getBlockedUntil().isBefore( LocalDateTime.now() )) {
            System.out.println("U mag weer inloggen");
        } else {
            System.out.println("U mag nog niet inloggen");
            return false;
        }

        if ( loginAttempt.getFailedAttempts() >= 3 ) {
            System.out.println( "Vaker dan 3 x fout ingelogd" );
            LocalDateTime blockedUntil = loginAttempt.getTimeAtLastLoginAttempt().plusMinutes( 15 );
            loginAttempt.setBlockedUntil( blockedUntil );
            loginForm.setLoginAttemptsError( String.format( "Vaker dan 3x fout ingelogd! Je mag pas weer inloggen om %s", blockedUntil.toString() ) );
            return false;
        }
        if ( customer != null ) {
            if ( customer.getPassword().equals( loginForm.getPassword() ) ) {
                logMessage = SUCCESS + " | " + customer;
                loginAttempt.resetFailedAttempts();
                loginAttemptDao.save( loginAttempt );
                System.out.println( loginAttempt );
                return true;
            }
        }
        logMessage = WRONGPASSWORD + " | " + loginForm.getPassword();
        loginForm.setPasswordError( LOGINERROR_PASSWORD );

        loginAttempt.incrementFailedAttempts();
        loginAttemptDao.save( loginAttempt );

        System.out.println( loginAttempt );

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
        return loginAttemptOptional.orElseGet( () -> new LoginAttempt( customer ) );

    }

}
