package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.controller.LoginController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginValidatorTest {

    @Autowired
    LoginController loginController;
    @Autowired
    CustomerService customerService;

    private final String WRONGUSERNAME = "FOUTEGEBRUIKERSNAAM";
    private final String WRONGPASSWORD = "FOUTWACHTWOORD";
    private final LoginForm WRONGLOGIN_FORM = new LoginForm( WRONGUSERNAME, WRONGPASSWORD );
    private final LoginForm WRONGPASSWORD_FORM = new LoginForm( "test", WRONGPASSWORD );
    private final LoginForm CORRECT_FORM = new LoginForm( "test", "test" );


    @BeforeEach
    void setUp() {
        loginController.fillDB();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validateCredentials() {
        LoginValidatorCustomer lv = customerService.validateCredentials( WRONGLOGIN_FORM );
        assertNotNull( lv );
        assertFalse( lv.isUserValidated() );
        assertFalse( lv.isPasswordValidated() );

        lv = customerService.validateCredentials( WRONGPASSWORD_FORM );
        assertNotNull( lv );
        assertTrue( lv.isUserValidated() );
        assertFalse( lv.isPasswordValidated() );

        lv = customerService.validateCredentials( CORRECT_FORM );
        assertNotNull( lv );
        assertTrue( lv.isUserValidated() );
        assertTrue( lv.isPasswordValidated() );
    }
}