package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.EmployeeDao;
import nl.dagobank.webapp.domain.Employee;
import nl.dagobank.webapp.domain.InlogCredentials;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

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
        assertThat( loginValidatorEmployee.getLogMessage() ).isNotEqualTo( "" );

        loginValidatorEmployee.validateCredentials( new LoginForm( "wrongUserName", "correctPassword" ) );

        assertThat( loginValidatorEmployee.isLoginValidated() ).isFalse();
        assertThat( loginValidatorEmployee.getLogMessage() ).isNotEqualTo( "" );

    }
}