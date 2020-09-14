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

        // Correct credentials test
        LoginForm loginForm = new LoginForm( "test", "test" );
        LoginValidatorEmployee loginValidatorEmployee = new LoginValidatorEmployee( mockEmployeeDao );
        loginValidatorEmployee.validateCredentials( loginForm );
        assertThat( loginValidatorEmployee.isLoginValidated() ).isTrue();

        // Wrong password + correct username test
        loginForm.setPassword( "wrongPassword" );
        loginValidatorEmployee.validateCredentials( loginForm );
        assertThat( loginValidatorEmployee.isLoginValidated() ).isFalse();
        assertThat( loginValidatorEmployee.getLogMessage() ).isNotNull().isNotEqualTo( "" ).isEqualTo( LoginValidatorEmployee.WRONG_CREDENTIALS_LOG_MESSAGE );
        assertThat( loginForm.getGeneralError() ).isNotNull().isNotEqualTo( "" ).isEqualTo( LoginValidatorEmployee.WRONG_CREDENTIALS_FORM_MESSAGE );

        // Wrong username + correct password test
        loginForm.setUsername( "wrongUsername" );
        loginForm.setPassword( "test" );
        loginValidatorEmployee.validateCredentials( new LoginForm( "wrongUserName", "correctPassword" ) );
        assertThat( loginValidatorEmployee.isLoginValidated() ).isFalse();
        assertThat( loginValidatorEmployee.getLogMessage() ).isNotNull().isNotEqualTo( "" );

    }
}