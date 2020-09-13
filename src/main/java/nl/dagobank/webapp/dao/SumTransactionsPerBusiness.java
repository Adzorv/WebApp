package nl.dagobank.webapp.dao;

import java.math.BigDecimal;

public interface SumTransactionsPerBusiness {
    BigDecimal getSumTransactions();
    String getBusinessName();
    Integer getDebitCount();
    Integer getCreditCount();
}
