package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.LoginAttemptDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.LoginAttempt;
import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.service.LoginValidatorCustomer;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith( SpringRunner.class )
@WebMvcTest( LoginController.class )
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;
    //    @MockBean
//    CustomerDao mockCustomerDao;
//    @MockBean
//    LoginAttemptDao mockLoginAttemptDao;
    @MockBean
    CustomerService mockCustomerService;
//    @MockBean
//    LoginValidatorCustomer loginValidatorCustomer;
//    @MockBean
//    LoginForm mockLoginForm;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void setup() {
//        Mockito.when( mockCustomerService.validateCredentials( mockLoginForm )).thenReturn( loginValidatorCustomer );
//        System.out.println( mockCustomerService.validateCredentials( mockLoginForm ) );
    }

    @Test
    void login() {


    }

    @Test
    void loginAttempt() {

        Mockito.when(mockCustomerService.checkIfBSNIsCorrect( 111222333 )).thenReturn( false );

        CustomerService customerServiceFromContext = context.getBean( CustomerService.class );

        boolean result = customerServiceFromContext.checkIfBSNIsCorrect( 111222333 );

        Assert.assertEquals(false, result);
        Mockito.verify(mockCustomerService).checkIfBSNIsCorrect( 111222333 );

        try {
            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/login");
            mockMvc.perform( request )
                    .andDo( print() );
//                    .andExpect( status().isOk() )
//                    .andExpect( view().name( LoginController.POSTLOGIN_VIEW ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void checkUsername() {
    }
}