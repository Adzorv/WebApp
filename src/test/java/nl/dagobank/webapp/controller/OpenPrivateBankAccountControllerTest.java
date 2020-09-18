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

        MockHttpSession session = new MockHttpSession();
        Customer user = new Customer();
        FullName fullNameCustomer = new FullName("Customer", "ter", "3");
        Address address = new Address("teststraat", 5, "","8888AA", "Testcity");
        PersonalDetails personalDetails = new PersonalDetails( LocalDate.of(1977,12,1),  111222333 );
        InlogCredentials inlogCredentialsCustomer = new InlogCredentials("Cus3", "cus3");

        user.setFullName(fullNameCustomer);
        user.setAddress(address);
        user.setPersonalDetails(personalDetails);
        user.setInlogCredentials(inlogCredentialsCustomer);
        session.setAttribute("user", user);

        try {
            MockHttpServletRequestBuilder getRequest =
                    MockMvcRequestBuilders.get("/openPrivateBankAccount").session(session);
            ResultActions resultActions = mockMvc.perform(getRequest);
            resultActions.andExpect(status().isOk()).andExpect(view().name("openPrivateBankAccount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
