package nl.dagobank.webapp.backingbeans;

public class Business {

    private String businessName;
    private int kvkNumber;
    private String sbiCode;

    public Business() {
        this("", 0, "");
    }

    public Business( String businessName, int kvkNumber, String sbiCode ) {
        super();
        this.businessName = businessName;
        this.kvkNumber = kvkNumber;
        this.sbiCode = sbiCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName( String businessName ) {
        this.businessName = businessName;
    }

    public int getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber( int kvkNumber ) {
        this.kvkNumber = kvkNumber;
    }

    public String getSbiCode() {
        return sbiCode;
    }

    public void setSbiCode( String sbiCode ) {
        this.sbiCode = sbiCode;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessName='" + businessName + '\'' +
                ", kvkNumber=" + kvkNumber +
                ", sbiCode='" + sbiCode + '\'' +
                '}';
    }
}
