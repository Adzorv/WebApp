package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    LoginValidatorEmployee loginValidator;

    public LoginValidatorEmployee validateCredentials( LoginForm loginForm ) {
        loginValidator.validateCredentials( loginForm );
        return loginValidator;
    }

}
