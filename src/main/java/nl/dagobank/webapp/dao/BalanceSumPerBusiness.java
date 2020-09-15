package nl.dagobank.webapp.dao;

import java.math.BigDecimal;

public interface BalanceSumPerBusiness {
    String getBusinessName();
    BigDecimal getBalance();
}
