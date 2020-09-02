package nl.dagobank.webapp.backingbeans;

public class BankAccountNameForm {

    private String bankAccountName;
    private String button;

    public BankAccountNameForm(String bankAccountName, String button) {
        this.bankAccountName = bankAccountName;
        this.button = button;
    }

    public BankAccountNameForm() {
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
