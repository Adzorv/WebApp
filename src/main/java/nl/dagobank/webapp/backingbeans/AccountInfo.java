package nl.dagobank.webapp.backingbeans;

public class AccountInfo {
    String iban;
    String accountName;

    public AccountInfo( String iban, String accountName ) {
        this.iban = iban;
        this.accountName = accountName;
    }

    public String getIban() {
        return iban;
    }

    public String getAccountName() {
        return accountName;
    }

}
