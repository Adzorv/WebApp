package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.service.BankAccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;

import javax.swing.text.View;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(OpenPrivateBankAccountController.class)
public class OpenPrivateBankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService bankAccountService;

    Customer mockCustomer = Mockito.mock(Customer.class);
    MockHttpSession session = new MockHttpSession();



    @BeforeEach
    public void setup() {
        session.setAttribute("user", mockCustomer);
    }

    @Test
    public void openBankAccountHandlerTest() {
        //PrivateAccount mockAccount = Mockito.mock(PrivateAccount.class);
        doNothing().when(bankAccountService).createAndSavePrivateAccount(any(),any(), any());
        try {
            MockHttpServletRequestBuilder getRequest =
                    MockMvcRequestBuilders.get("/openPrivateBankAccount").session(session);
            ResultActions resultActions = mockMvc.perform(getRequest);
            resultActions.andDo(print()).andExpect(status().isOk()).andExpect(view().name("openPrivateBankAccount"));

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void openBankAccountSuccessHandlerTest(){

        try {
            MockHttpServletRequestBuilder postRequest =
                    MockMvcRequestBuilders.post("/openAndSaveBankAccount");
            postRequest.param("bankAccountName", "testname");
            ResultActions resultActions = mockMvc.perform(postRequest);
            resultActions.andDo(print()).andExpect(status().isOk()).andExpect(view().name("openPrivateBankAccountSuccess"));

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
