package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.dao.PrivateAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.UnsupportedCountryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IbanGenerator {
    Iban iban;

    @Autowired
    public PrivateAccountDao privateAccountDao;

    public IbanGenerator() {
    }

    public Iban createIban() {
        while (!privateAccountDao.existsByIban(iban)) {
            try {
                Iban iban = Iban.random(CountryCode.NL);
                iban = Iban.random();
                iban = new Iban.Builder()
                        .countryCode(CountryCode.NL)
                        .bankCode("DAGO")
                        .buildRandom();
                return iban;
            } catch (IbanFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (UnsupportedCountryException e) {
                e.printStackTrace();
            }
        }
        return iban;
    }

    public Iban getIban() {
        return iban;
    }

    public void setIban(Iban iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return iban.toString();
    }
}
