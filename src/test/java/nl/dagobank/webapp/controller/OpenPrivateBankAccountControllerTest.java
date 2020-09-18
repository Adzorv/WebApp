package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.service.BankAccountService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(OpenPrivateBankAccountController.class)
public class OpenPrivateBankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService bankAccountService;

    @Test
    public void openBankAccountHandlerTest() {
        Customer mockCustomer = Mockito.mock(Customer.class);
        MockHttpSession session = new MockHttpSession();

        session.setAttribute("user", mockCustomer);

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
}
