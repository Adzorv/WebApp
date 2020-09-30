package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ConnectBankAccountForm;
import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.LoginAttemptDao;
import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.service.FacadeServiceBankAccountHolderToken_BancAccount;
import nl.dagobank.webapp.exception.*;
import nl.dagobank.webapp.service.LoginValidatorCustomer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest( LoginController.class )
class LoginControllerTest {


    @MockBean
    CustomerService customerService;
//    @MockBean
//    LoginAttemptDao mockLoginAttemtDao;
    @MockBean
    LoginValidatorCustomer mockLoginValidatorCustomer;

    @Autowired
    LoginController loginController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        when(customerService.validateCredentials( any() )).thenReturn( mockLoginValidatorCustomer );

    }

    @Test
    void login() {
        MockHttpServletRequestBuilder get = MockMvcRequestBuilders.get( "/login" );
        try {
            mockMvc.perform( get )
                    .andExpect( status().isOk() )
                    .andExpect( view().name( "login" ) )
            ;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    void loginAttemptSucces() {
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/login");
        when(mockLoginValidatorCustomer.isUserValidated()).thenReturn( true );
        when(mockLoginValidatorCustomer.isPasswordValidated()).thenReturn( true );
        try {
            mockMvc.perform( post )
                    .andExpect( status().is3xxRedirection() )
                    .andExpect( view().name( LoginController.POSTLOGIN_REDIRECT ) );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    void loginAttemptUserNotValidated() {
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/login");
        when(mockLoginValidatorCustomer.isUserValidated()).thenReturn( false );
        when(mockLoginValidatorCustomer.isPasswordValidated()).thenReturn( true );
        try {
            mockMvc.perform( post )
                    .andExpect( status().isOk() )
                    .andExpect( view().name( LoginController.LOGIN_VIEW ));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    void loginAttemptPasswordNotValidated() {
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/login");
        when(mockLoginValidatorCustomer.isUserValidated()).thenReturn( true );
        when(mockLoginValidatorCustomer.isPasswordValidated()).thenReturn( false );
        try {
            mockMvc.perform( post )
                    .andExpect( status().isOk() )
                    .andExpect( view().name( LoginController.LOGIN_VIEW ));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    void loginAttemptUserAndPasswordNotValidated() {
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/login");
        when(mockLoginValidatorCustomer.isUserValidated()).thenReturn( false );
        when(mockLoginValidatorCustomer.isPasswordValidated()).thenReturn( false );
        try {
            mockMvc.perform( post )
                    .andExpect( status().isOk() )
                    .andExpect( view().name( LoginController.LOGIN_VIEW ));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}