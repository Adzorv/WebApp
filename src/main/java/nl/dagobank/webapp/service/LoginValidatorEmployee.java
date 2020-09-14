package nl.dagobank.webapp.service;


import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.EmployeeDao;
import nl.dagobank.webapp.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginValidatorEmployee {

    @Autowired
    private EmployeeDao employeeDao;

    private Employee employee;

    private boolean loginValidated = false;
    private String logMessage;

    public void validateCredentials( LoginForm loginForm ) {
        Optional<Employee> optional = employeeDao.findByUserInlogCredentialsUserName( loginForm.getUsername() );
        if ( optional.isPresent() ) {
            employee = optional.get();
            if ( employee.getUserInlogCredentials().getPassword().equals( loginForm.getPassword() )) {
                loginValidated = true;
                logMessage = "Gebruikersnaam en wachtwoord correct";
                return;
            }
        }
        logMessage = "Foute gebruikersnaam en/of wachtwoord";
        loginForm.setGeneralError( "Foute gebruikersnaam en/of wachtwoord" );
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
}
