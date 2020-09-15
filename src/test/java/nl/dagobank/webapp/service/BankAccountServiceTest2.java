package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.domain.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccountServiceTest2 {

    private BankAccountDao mockBankAccountDao = Mockito.mock(BankAccountDao.class);
    private BusinessAccountDao mockBusinessAccountDao = Mockito.mock(BusinessAccountDao.class);

    BankAccountService bankAccountService = new BankAccountService(mockBankAccountDao, mockBusinessAccountDao);

    @Test
    void getAllAccountsFromCustomerTest(){

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();

        FullName fullNameCustomer1 = new FullName("Customer", "van", "1");
        FullName fullNameCustomer2 = new FullName("Customer", "de", "2");

        InlogCredentials inlogCredentialsCustomer1 = new InlogCredentials("Cus1", "cus1");
        InlogCredentials inlogCredentialsCustomer2 = new InlogCredentials("Cus2", "cus2");

        customer1.setFullName(fullNameCustomer1);
        customer2.setFullName(fullNameCustomer2);

        customer1.setInlogCredentials(inlogCredentialsCustomer1);
        customer2.setInlogCredentials(inlogCredentialsCustomer2);

        BankAccount bankAccount1 = new PrivateAccount();
        bankAccount1.setAccountName("Private Account 1");
        bankAccount1.setId(1);
        bankAccount1.setBalance(new BigDecimal(25));
        bankAccount1.setIban("IBAN1111111111");
        bankAccount1.setAccountHolder(customer2);
        System.out.println("print bankaccount");
        System.out.println(bankAccount1);

        BankAccount bankAccount2 = new PrivateAccount();
        bankAccount2.setAccountName("Private Account 1");
        bankAccount2.setId(2);
        bankAccount2.setBalance(new BigDecimal(25));
        bankAccount2.setIban("IBAN2222222222");
        bankAccount2.setAccountHolder(customer2);
        System.out.println("print bankaccount");
        System.out.println(bankAccount2);

        BankAccount bankAccount3 = new PrivateAccount();
        bankAccount3.setAccountName("Private Account 1");
        bankAccount3.setId(3);
        bankAccount3.setBalance(new BigDecimal(25));
        bankAccount3.setIban("IBAN3333333333");
        bankAccount3.setAccountHolder(customer1);
        System.out.println("print bankaccount");
        System.out.println(bankAccount3);

        bankAccount1.setSecondaryAccountHolders(new ArrayList<>());
        bankAccount2.setSecondaryAccountHolders(new ArrayList<>());
        bankAccount1.setSecondaryAccountHolders(new ArrayList<>());

        bankAccount1.getSecondaryAccountHolders().add(customer1);
        bankAccount2.getSecondaryAccountHolders().add(customer2);
        bankAccount1.getSecondaryAccountHolders().add(customer2);


        List<BankAccount> bankAccountsList = new ArrayList<>();
        bankAccountsList.add(bankAccount1);
        bankAccountsList.add(bankAccount2);
        bankAccountsList.add(bankAccount3);

        Mockito.when(mockBankAccountDao.findAllByAccountHolderOrSecondaryAccountHoldersContains(customer1, customer1)).thenReturn(bankAccountsList);

        List<BankAccount> receivedBankAccounts = bankAccountService.getAllAccountsFromCustomer(customer1);

        List<BankAccount> expectedBankAccounts = new ArrayList<>();
        expectedBankAccounts.add(bankAccount3);
        expectedBankAccounts.add(bankAccount1);
        expectedBankAccounts.add(bankAccount2);

        Assert.assertEquals(expectedBankAccounts, receivedBankAccounts);
    }
}