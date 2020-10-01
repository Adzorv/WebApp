package nl.dagobank.webapp.domain;

import nl.dagobank.webapp.backingbeans.AccountInfo;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class PrivateAccount extends BankAccount {


    public PrivateAccount( AccountInfo accountInfo, Customer accountHolder ) {
        super( accountInfo, accountHolder );
    }

    public PrivateAccount() {
        super();
    }

    @Override
    public String toString() {
        return "Particulier rekening: " + super.toString();
    }
}
