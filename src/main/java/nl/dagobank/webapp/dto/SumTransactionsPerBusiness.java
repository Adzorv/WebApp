package nl.dagobank.webapp.dto;

import java.math.BigDecimal;

public interface SumTransactionsPerBusiness {
    BigDecimal getSumTransactions();
    String getBusinessName();
    Integer getDebitCount();
    Integer getCreditCount();
}
