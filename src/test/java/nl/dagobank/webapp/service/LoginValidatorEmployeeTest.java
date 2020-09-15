package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.EmployeeDao;
import nl.dagobank.webapp.domain.Employee;
import nl.dagobank.webapp.domain.InlogCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginValidatorEmployeeTest {

    private LoginForm loginForm;
    private LoginValidatorEmployee loginValidatorEmployee;


    @BeforeEach
    public void setUp() {
        Employee employee = new Employee();
        employee.setInlogCredentials( new InlogCredentials( "test", "test" ) );
        EmployeeDao mockEmployeeDao = mock( EmployeeDao.class );
        when( mockEmployeeDao.findByInlogCredentialsUserName( "test" ) ).thenReturn( Optional.of( employee ) );
        loginValidatorEmployee = new LoginValidatorEmployee( mockEmployeeDao );
        loginForm = new LoginForm( "test", "test" );
    }

    @Test
    void validateCredentials() {
        // Correct credentials test
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

        // Correct credentials after wrong credentials test
        loginForm.setUsername( "test" );
        loginForm.setPassword( "test" );
        loginValidatorEmployee.validateCredentials( loginForm );
        assertThat( loginValidatorEmployee.isLoginValidated() ).isTrue();
    }
}