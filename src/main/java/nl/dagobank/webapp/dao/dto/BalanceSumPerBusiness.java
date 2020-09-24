package nl.dagobank.webapp.dao.dto;

import java.math.BigDecimal;

public interface BalanceSumPerBusiness {
    String getBusinessName();
    BigDecimal getBalance();
    int getKvkNumber();
}
