package nl.dagobank.webapp.service;


import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.EmployeeDao;
import nl.dagobank.webapp.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginValidatorEmployee {

    private EmployeeDao employeeDao;
    private Employee employee;
    private boolean loginValidated;
    private String logMessage;
    public static final String WRONG_CREDENTIALS_LOG_MESSAGE,
            LOGIN_SUCCESS_LOG_MESSAGE,
            WRONG_CREDENTIALS_FORM_MESSAGE;

    static {
        WRONG_CREDENTIALS_LOG_MESSAGE = "Foute gebruikersnaam en/of wachtwoord";
        LOGIN_SUCCESS_LOG_MESSAGE = "Gebruikersnaam en wachtwoord correct";
        WRONG_CREDENTIALS_FORM_MESSAGE = WRONG_CREDENTIALS_LOG_MESSAGE;
    }

    @Autowired
    public LoginValidatorEmployee( EmployeeDao employeeDao ) {
        this.employeeDao = employeeDao;
        loginValidated = false;
        logMessage = WRONG_CREDENTIALS_LOG_MESSAGE;
        employee = null;
    }

    public void validateCredentials( LoginForm loginForm ) {
        reset();
        loginForm.setGeneralError( WRONG_CREDENTIALS_FORM_MESSAGE );
        Optional<Employee> optional = employeeDao.findByInlogCredentialsUserName( loginForm.getUsername() );
        if ( optional.isPresent() ) {
            employee = optional.get();
            if ( employee.getInlogCredentials().getPassword().equals( loginForm.getPassword() ) ) {
                loginValidated = true;
                logMessage = "Gebruikersnaam en wachtwoord correct";
                loginForm.setGeneralError( "" );
            }
        }
    }

    public boolean isLoginValidated() {
        return loginValidated;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getLogMessage() {
        return logMessage;
    }

    private void reset() {
        logMessage = WRONG_CREDENTIALS_LOG_MESSAGE;
        employee = null;
        loginValidated = false;
    }
}
