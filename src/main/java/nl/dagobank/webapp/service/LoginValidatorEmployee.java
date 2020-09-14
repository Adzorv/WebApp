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

    @Autowired
    public LoginValidatorEmployee( EmployeeDao employeeDao ) {
        this.employeeDao = employeeDao;
        loginValidated = false;
        logMessage = "Foute gebruikersnaam en/of wachtwoord";
        employee = null;
    }

    public void validateCredentials( LoginForm loginForm ) {
        reset();
        Optional<Employee> optional = employeeDao.findByInlogCredentialsUserName( loginForm.getUsername() );
        if ( optional.isPresent() ) {
            employee = optional.get();
            if ( employee.getInlogCredentials().getPassword().equals( loginForm.getPassword() ) ) {
                loginValidated = true;
                logMessage = "Gebruikersnaam en wachtwoord correct";
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

    public void reset() {
        logMessage = "Foute gebruikersnaam en/of wachtwoord";
        employee = null;
        loginValidated = false;
    }
}
