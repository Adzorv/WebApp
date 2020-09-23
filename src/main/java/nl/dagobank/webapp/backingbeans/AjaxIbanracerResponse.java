package nl.dagobank.webapp.backingbeans;

import nl.dagobank.webapp.domain.BankAccount;

import java.util.List;

public class AjaxIbanracerResponse {
    private String msg;
    private List<BankAccount> bankAccounts;

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts( List<BankAccount> bankAccounts ) {
        this.bankAccounts = bankAccounts;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }
}
