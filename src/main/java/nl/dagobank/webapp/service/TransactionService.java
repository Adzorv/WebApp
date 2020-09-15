package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.SumTransactionsPerBusiness;
import nl.dagobank.webapp.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private TransactionDao transactionDao;

    @Autowired
    public TransactionService( TransactionDao transactionDao ) {
        this.transactionDao = transactionDao;
    }

    public List<SumTransactionsPerBusiness> getTop10SumTransactions() {
        return transactionDao.findSumOfTransactsionsPerBusinessAccount( PageRequest.of( 0, 10 ) );
    }
}
