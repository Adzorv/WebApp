package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.domain.*;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BankAccountServiceTest {

    private BankAccountDao mockBankAccountDao = Mockito.mock(BankAccountDao.class);
    private BusinessAccountDao mockBusinessAccountDao = Mockito.mock(BusinessAccountDao.class);
    private IbanGenerator mockIbanGenerator = Mockito.mock(IbanGenerator.class);


    BankAccountService bankAccountService = new BankAccountService(mockBankAccountDao, mockBusinessAccountDao, mockIbanGenerator);

    Customer customer1 = new Customer();
    Customer customer2 = new Customer();
    Customer customer3 = new Customer();

    FullName fullNameCustomer1 = new FullName("Customer", "van", "1");
    FullName fullNameCustomer2 = new FullName("Customer", "de", "2");
    FullName fullNameCustomer3 = new FullName("Customer", "ter", "3");

    InlogCredentials inlogCredentialsCustomer1 = new InlogCredentials("Cus1", "cus1");
    InlogCredentials inlogCredentialsCustomer2 = new InlogCredentials("Cus2", "cus2");
    InlogCredentials inlogCredentialsCustomer3 = new InlogCredentials("Cus3", "cus3");


    BankAccount bankAccount1 = new PrivateAccount();
    BankAccount bankAccount2 = new PrivateAccount();
    BankAccount bankAccount3 = new PrivateAccount();
    BankAccount bankAccount4 = new PrivateAccount();

    @BeforeEach void setup() {

        customer1.setFullName(fullNameCustomer1);
        customer2.setFullName(fullNameCustomer2);
        customer3.setFullName((fullNameCustomer3));

        customer1.setInlogCredentials(inlogCredentialsCustomer1);
        customer2.setInlogCredentials(inlogCredentialsCustomer2);
        customer3.setInlogCredentials(inlogCredentialsCustomer3);

        bankAccount1.setAccountName("Private Account 1");
        bankAccount1.setId(1);
        bankAccount1.setBalance(new BigDecimal(25));
        bankAccount1.setIban("IBAN1111111111");
        bankAccount1.setAccountHolder(customer2);

        bankAccount2.setAccountName("Private Account 2");
        bankAccount2.setId(2);
        bankAccount2.setBalance(new BigDecimal(25));
        bankAccount2.setIban("IBAN2222222222");
        bankAccount2.setAccountHolder(customer2);

        bankAccount3.setAccountName("Private Account 3");
        bankAccount3.setId(3);
        bankAccount3.setBalance(new BigDecimal(25));
        bankAccount3.setIban("IBAN3333333333");
        bankAccount3.setAccountHolder(customer1);

        bankAccount4.setAccountName("Private Account 4");
        bankAccount4.setId(3);
        bankAccount4.setBalance(new BigDecimal(25));
        bankAccount4.setIban("IBAN4444444444");
        bankAccount4.setAccountHolder(customer3);

        bankAccount1.setSecondaryAccountHolders(new ArrayList<>());
        bankAccount2.setSecondaryAccountHolders(new ArrayList<>());
        bankAccount3.setSecondaryAccountHolders(new ArrayList<>());
        bankAccount4.setSecondaryAccountHolders(new ArrayList<>());

        bankAccount1.getSecondaryAccountHolders().add(customer1);
        bankAccount2.getSecondaryAccountHolders().add(customer2);
        bankAccount1.getSecondaryAccountHolders().add(customer2);
    }

    @Test
    void getAllAccountsFromCustomerTest(){

        List<BankAccount> bankAccountsList = new ArrayList<>();
        bankAccountsList.add(bankAccount1);
        bankAccountsList.add(bankAccount2);
        bankAccountsList.add(bankAccount3);

        when(mockBankAccountDao.findAllByAccountHolderOrSecondaryAccountHoldersContains(customer1, customer1)).thenReturn(bankAccountsList);

        List<BankAccount> receivedBankAccounts = bankAccountService.getAllAccountsFromCustomer(customer1);

        List<BankAccount> expectedBankAccounts = new ArrayList<>();
        expectedBankAccounts.add(bankAccount3);
        expectedBankAccounts.add(bankAccount1);
        expectedBankAccounts.add(bankAccount2);

        Assert.assertEquals(expectedBankAccounts, receivedBankAccounts);
    }

    @Test
    void isCustomerFirstOrSecundairyAccountHolderTest1(){
        boolean actual = bankAccountService.isCustomerFirstOrSecundairyAccountHolder(customer1, bankAccount1);
        Assert.assertTrue(actual);
    }

    @Test
    void isCustomerFirstOrSecundairyAccountHolderTest2(){
        boolean actual = bankAccountService.isCustomerFirstOrSecundairyAccountHolder(customer2, bankAccount2);
        Assert.assertTrue(actual);
    }

    @Test
    void isCustomerFirstOrSecundairyAccountHolderTest3(){
        boolean actual = bankAccountService.isCustomerFirstOrSecundairyAccountHolder(customer1, bankAccount3);
        Assert.assertTrue(actual);
    }

    @Test
    void isCustomerFirstOrSecundairyAccountHolderTest4(){
        boolean actual = bankAccountService.isCustomerFirstOrSecundairyAccountHolder(customer1, bankAccount4);
        Assert.assertFalse(actual);
    }

    @Test
    void generateBankAccountNameAndPutInModelTest(){
        Model mockModel = Mockito.mock(Model.class);
        when(mockModel.getAttribute("user")).thenReturn(customer1);

        List<BankAccount> bankAccountsList = new ArrayList<>();
        bankAccountsList.add(bankAccount1);

        when(mockBankAccountDao.findAllByAccountHolder(customer1)).thenReturn(bankAccountsList);

        ModelAndView modelAndView = new ModelAndView("testmodelandView");

        bankAccountService.generateBankAccountNameAndPutInModel(mockModel, modelAndView);

        String found = modelAndView.getModel().get("bankAccountName").toString();
        String expected = customer1.getFullName() + "'s rekening 2";
        Assert.assertEquals(found,expected);
    }

    @Test
    void createAndSavePrivateAccountTest(){
        Iban iban = new Iban.Builder().buildRandom();
        PrivateAccount mockBankAccount = Mockito.mock(PrivateAccount.class);
        Model mockModel = Mockito.mock(Model.class);
        when(mockModel.getAttribute("user")).thenReturn(customer1);
        when(mockIbanGenerator.createIban()).thenReturn(iban);
        PrivateAccount actual = bankAccountService.createAndSavePrivateAccount("testBankAccountName", mockModel);

        Assert.assertNotNull(actual);
        Assert.assertEquals(actual.getAccountName(), "testBankAccountName");
        Assert.assertEquals(actual.getAccountHolder(), customer1);

        }
}