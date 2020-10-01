package nl.dagobank.webapp.dto;


import nl.dagobank.webapp.domain.Customer;

import java.math.BigDecimal;

public interface SumBalancePrivateAccounts {
    BigDecimal getSumBalance();
    Customer getAccountHolder();
}
