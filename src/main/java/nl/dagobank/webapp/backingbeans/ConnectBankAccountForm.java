package nl.dagobank.webapp.backingbeans;

public class ConnectBankAccountForm {

        String iban;
        String connectionCode;

        public ConnectBankAccountForm() {
        }

        public String getIban() {
                return iban;
        }

        public void setIban(String iban) {
                this.iban = iban;
        }

        public String getConnectionCode() {
                return connectionCode;
        }

        public void setConnectionCode(String connectionCode) {
                this.connectionCode = connectionCode;
        }
}
