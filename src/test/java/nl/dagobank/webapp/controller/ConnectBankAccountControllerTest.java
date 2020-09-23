package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.ConnectBankAccountForm;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.FacadeServiceBankAccountHolderToken_BancAccount;
import nl.dagobank.webapp.exception.*
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ConnectBankAccountController.class)
public class ConnectBankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ConnectBankAccountForm connectBankAccountForm;
    Customer customer;

    FacadeServiceBankAccountHolderToken_BancAccount mockService = Mockito.mock(FacadeServiceBankAccountHolderToken_BancAccount.class);

    @BeforeAll
    void setup(){
        customer = Mockito.mock(Customer.class);
        connectBankAccountForm = Mockito.mock(ConnectBankAccountForm.class);

    }

    @Test
    public void connectBankAccountHandlerTest() {
    Mockito.when(mockService.addUserAsSecondaryAccountHolder(any(), any(), any())).thenThrow(new noValidTokenException("noValidTokenException"));
    try {
            MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/connectBankAccount");
            postRequest.param("connectBankAccountForm", connectBankAccountForm);

            ResultActions resultActions = mockMvc.perform(postRequest);
            resultActions.andDo(print()).andExpect(status().isOk()).andExpect(view().name("openPrivateBankAccount"));

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


}
