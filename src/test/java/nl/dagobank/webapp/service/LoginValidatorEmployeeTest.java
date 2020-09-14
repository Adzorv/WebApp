package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.dao.EmployeeDao;
import nl.dagobank.webapp.domain.Employee;
import nl.dagobank.webapp.domain.InlogCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginValidatorEmployeeTest {


    @Test
    void validateCredentials() {
        Employee employee = new Employee();
        employee.setInlogCredentials( new InlogCredentials( "test", "test" ) );

        EmployeeDao mockEmployeeDao = mock( EmployeeDao.class );
        when( mockEmployeeDao.findByInlogCredentialsUserName( "test" ) ).thenReturn( Optional.of( employee ) );

        LoginForm loginForm = new LoginForm( "test", "test" );

        LoginValidatorEmployee loginValidatorEmployee = new LoginValidatorEmployee( mockEmployeeDao );
        loginValidatorEmployee.validateCredentials( loginForm );

        assertThat( loginValidatorEmployee.isLoginValidated() ).isTrue();

        loginValidatorEmployee.validateCredentials( new LoginForm( "test", "wrongPassword" ) );

        assertThat( loginValidatorEmployee.isLoginValidated() ).isFalse();

        loginValidatorEmployee.validateCredentials( new LoginForm( "wrongUserName", "correctPassword" ) );

        assertThat( loginValidatorEmployee.isLoginValidated() ).isFalse();

    }
}