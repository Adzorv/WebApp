package nl.dagobank.webapp.dto;

import java.math.BigDecimal;

public interface BalanceSumPerBusiness {
    String getBusinessName();
    BigDecimal getBalance();
    int getKvkNumber();
}
