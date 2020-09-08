package nl.dagobank.webapp.backingbeans;

public class AddAdditionalBankAccountHolderForm {

    String loginNameAdditionalAccountHolder;
    String connectionCode;

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
}
