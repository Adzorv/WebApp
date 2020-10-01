/*
package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.LoginAttemptDao;
import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.service.LoginValidatorCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest( LoginController.class )
class LoginControllerIntegrationLoginValidatorTest {


    @MockBean
    CustomerDao mockCustomerDao;
    @MockBean
    LoginAttemptDao mockLoginAttemptDao;

    LoginValidatorCustomer loginValidatorCustomer;
    CustomerService customerService;

    @Autowired
    LoginController loginController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        loginValidatorCustomer = new LoginValidatorCustomer( mockCustomerDao, mockLoginAttemptDao );
        customerService = new CustomerService( mockCustomerDao, loginValidatorCustomer );
    }

    @Test
    void loginWithLoginValidator() {
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/login");

        try {
            mockMvc.perform(post).andDo( print() );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}*/
