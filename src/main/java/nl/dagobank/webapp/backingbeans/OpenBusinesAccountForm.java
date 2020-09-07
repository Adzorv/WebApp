package nl.dagobank.webapp.backingbeans;

public class OpenBusinesAccountForm {
    String BusinessName;
    String kvkNumber;
    String sdiNumber;

    public OpenBusinesAccountForm() {
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber(String kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public String getSdiNumber() {
        return sdiNumber;
    }

    public void setSdiNumber(String sdiNumber) {
        this.sdiNumber = sdiNumber;
    }
}
