//TODO refactor to local H2 database DataJPATest
/*

package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.dao.dto.SumTransactionsPerBusiness;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.*;


import java.util.List;

@SpringBootTest
class TransactionDaoTest {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    BankAccountDao bankAccountDao;
    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = bankAccountDao.findById( 30 ).get();
    }

    @Test
    void findAllByDebitAccount() {
        List<Transaction> transactions = transactionDao.findAllByDebitAccount( bankAccount );
        assertThat( transactions ).isNotEmpty();
    }

    @Test
    void findAllByCreditAccount() {
        List<Transaction> transactions = transactionDao.findAllByCreditAccount( bankAccount );
        assertThat( transactions ).isNotEmpty();
    }

    @Test
    void findAllByDebitAccountOrCreditAccount() {
        List<Transaction> transactions = transactionDao.findAllByDebitAccountOrCreditAccountOrderByDate( bankAccount, bankAccount );
        List<Transaction> transactions2 = transactionDao.findAllByDebitAccount( bankAccount );
        assertThat( transactions ).isNotEmpty();
        assertThat( transactions.size() ).isGreaterThan( transactions2.size() );
        System.out.println( transactions.size() );
        System.out.println( transactions2.size() );
    }

    @Test
    void findSumOfTransactsionsPerBusinessAccount() {
        List<SumTransactionsPerBusiness> aggregationResult = transactionDao.findSumOfTransactsionsPerBusinessAccount( PageRequest.of( 0, 10 ) );
        assertThat(aggregationResult).isNotNull().isNotEmpty();
        for ( SumTransactionsPerBusiness sumTransactionsPerBusiness : aggregationResult ) {
            System.out.print(sumTransactionsPerBusiness.getBusinessName());
            System.out.println(sumTransactionsPerBusiness.getSumTransactions());
        }
    }
}
*/
