package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ConnectBankAccountForm;
import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.service.FacadeServiceBankAccountHolderToken_BancAccount;
import nl.dagobank.webapp.exception.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ConnectBankAccountController.class)
public class ConnectBankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    Customer customer1 = new Customer();
    ConnectBankAccountForm connectBankAccountForm;
    Customer customer;

    @MockBean
    FacadeServiceBankAccountHolderToken_BancAccount mockService;

    @BeforeEach
    void setup(){
        customer = Mockito.mock(Customer.class);
        connectBankAccountForm = Mockito.mock(ConnectBankAccountForm.class);

        FullName fullNameCustomer1 = new FullName("Customer", "van", "1");
        InlogCredentials inlogCredentialsCustomer1 = new InlogCredentials("Cus1", "cus1");
        customer1.setFullName(fullNameCustomer1);
        customer1.setInlogCredentials(inlogCredentialsCustomer1);

    }

    @Test
    public void connectBankAccountHandlerTest() throws customerAlreadySecondaryAccountHolderException, noValidTokenException {

        doThrow(new noValidTokenException("noValidTokenException")).when(mockService).addUserAsSecondaryAccountHolder(any(),any(),any());
        try {
            MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/connectBankAccount");
            postRequest.contentType(MediaType.APPLICATION_FORM_URLENCODED);
            postRequest.param("iban","IBAN_TEST");
            postRequest.param("connectionCode", "11111");
            postRequest.flashAttr("user", customer);

            doThrow(new noValidTokenException("noValidTokenException")).when(mockService).addUserAsSecondaryAccountHolder(any(),any(),any());
            ResultActions resultActions = mockMvc.perform(postRequest);
            resultActions.andExpect(status().isOk()).andExpect(view().name("connectBankAccount")).andExpect(MockMvcResultMatchers.model().attribute("error","IBAN en/of code zijn niet correct of je bent niet gemachtigd door de rekeninghouder."));

            doThrow(new customerAlreadySecondaryAccountHolderException("customerAlreadySecondaryAccountHolderException")).when(mockService).addUserAsSecondaryAccountHolder(any(),any(),any());
            ResultActions resultActions2 = mockMvc.perform(postRequest);
            resultActions2.andExpect(status().isOk()).andExpect(view().name("connectBankAccount")).andExpect(MockMvcResultMatchers.model().attribute("error","Je bent al Mederekeninghouder."));

            doNothing().when(mockService).addUserAsSecondaryAccountHolder(any(),any(),any());
            ResultActions resultActions3 = mockMvc.perform(postRequest);
            resultActions3.andExpect(status().isOk()).andExpect(view().name("bankAccountConnectionSuccess"));

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
