package nl.dagobank.webapp.backingbeans;

import nl.dagobank.webapp.domain.BankAccount;

public class AddAdditionalBankAccountHolderForm {

    String loginNameAdditionalAccountHolder;
    String connectionCode;
    int selectedBankAccountId;

    public AddAdditionalBankAccountHolderForm() {
    }

    public String getLoginNameAdditionalAccountHolder() {
        return loginNameAdditionalAccountHolder;
    }

    public void setLoginNameAdditionalAccountHolder(String loginNameAdditionalAccountHolder) {
        this.loginNameAdditionalAccountHolder = loginNameAdditionalAccountHolder;
    }

    public String getConnectionCode() {
        return connectionCode;
    }

    public void setConnectionCode(String connectionCode) {
        this.connectionCode = connectionCode;
    }

    public int getSelectedBankAccountId() {
        return selectedBankAccountId;
    }

    public void setSelectedBankAccountId(int selectedBankAccountId) {
        this.selectedBankAccountId = selectedBankAccountId;
    }
}
