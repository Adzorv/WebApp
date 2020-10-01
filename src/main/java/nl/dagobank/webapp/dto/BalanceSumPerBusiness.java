package nl.dagobank.webapp.dto;

import java.math.BigDecimal;

public interface BalanceSumPerBusiness {
    BigDecimal getBalance();
    int getKvkNumber();
}
