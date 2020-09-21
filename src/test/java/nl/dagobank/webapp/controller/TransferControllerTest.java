package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.TransactionDao;
import nl.dagobank.webapp.domain.*;
import nl.dagobank.webapp.service.TransferService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferControllerTest {

    private BankAccountDao mockBankAccountDao = Mockito.mock(BankAccountDao.class);
    private TransactionDao mockTransactionDao = Mockito.mock(TransactionDao.class);

    private TransferService transferService = new TransferService(mockTransactionDao, mockBankAccountDao);

    Customer customer1 = new Customer();
    Customer customer2 = new Customer();
    FullName fullNameCustomer1 = new FullName("Customer", "van", "1");
    FullName fullNameCustomer2 = new FullName("Customer", "de", "2");
    InlogCredentials inlogCredentialsCustomer1 = new InlogCredentials("Cus1", "cus1");
    InlogCredentials inlogCredentialsCustomer2 = new InlogCredentials("Cus2", "cus2");
    BankAccount bankAccount1 = new PrivateAccount();
    BankAccount bankAccount2 = new PrivateAccount();

    @BeforeEach
    void setup() {
        customer1.setFullName(fullNameCustomer1);
        customer2.setFullName(fullNameCustomer2);
        customer1.setInlogCredentials(inlogCredentialsCustomer1);
        customer2.setInlogCredentials(inlogCredentialsCustomer2);
        bankAccount1.setAccountName("Private Account 1");
        bankAccount1.setId(1);
        bankAccount1.setBalance(new BigDecimal(100));
        bankAccount1.setIban("IBAN1111111111");
        bankAccount1.setAccountHolder(customer2);
        System.out.println("print bankaccount");
        System.out.println(bankAccount1);

        bankAccount2.setAccountName("Private Account 2");
        bankAccount2.setId(2);
        bankAccount2.setBalance(new BigDecimal(100));
        bankAccount2.setIban("IBAN2222222222");
        bankAccount2.setAccountHolder(customer2);
        System.out.println("print bankaccount");
        System.out.println(bankAccount2);
    }

    @Test
    public void testTransferSuccessful(){
        transferService.performTransaction(bankAccount1, bankAccount2, new BigDecimal(50),
                "test 1");
        Assert.assertEquals(new BigDecimal(50), bankAccount1.getBalance());
        Assert.assertEquals(new BigDecimal(150), bankAccount2.getBalance());
    }

    @Test
    public void testInsufficientFund(){
        boolean isSuccess = transferService.performTransaction(bankAccount1, bankAccount2, new BigDecimal(500),
                "test 1");
        Assert.assertFalse(isSuccess);

    }
}

