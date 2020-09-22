package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.TransferService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TransferController.class)
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService bankAccountService;

    @MockBean
    private TransferService transferService;

    Customer mockCustomer = Mockito.mock(Customer.class);
    MockHttpSession session = new MockHttpSession();

    @BeforeEach
    public void setup() {
        session.setAttribute("user", mockCustomer);
    }

    /*@Test
    public void performTransferTest() {
        PrivateAccount privateAccount = Mockito.mock(PrivateAccount.class);
        Mockito.when(bankAccountService.createAndSavePrivateAccount(any(), any())).thenReturn(privateAccount);
        try {
            MockHttpServletRequestBuilder postRequest =
                    MockMvcRequestBuilders.post("/executeTransfer{id}");
            postRequest.param("id", session.getId());
            postRequest.session(session);
            ResultActions resultActions = mockMvc.perform(postRequest);
            resultActions.andDo(print()).andExpect(status().isOk()).andExpect(view().name("transactionOverview"));

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }*/
}