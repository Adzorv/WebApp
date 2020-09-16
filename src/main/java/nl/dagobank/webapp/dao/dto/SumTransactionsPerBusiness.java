package nl.dagobank.webapp.dao.dto;

import java.math.BigDecimal;

public interface SumTransactionsPerBusiness {
    BigDecimal getSumTransactions();
    String getBusinessName();
    Integer getDebitCount();
    Integer getCreditCount();
}
