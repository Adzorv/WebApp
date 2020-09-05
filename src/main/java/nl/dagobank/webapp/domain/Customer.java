package nl.dagobank.webapp.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class
Customer extends User {

    public Customer() {
        super();
    }

    public Customer (String firstName, String prefix, String lastName, String phoneNumber, String streetName, int houseNumber,
                     String houseNumberAnnex, String postCode, String city, String email, LocalDate birthDate, int bsn, String userName, String password) {
        super(firstName, prefix, lastName, phoneNumber, streetName, houseNumber, houseNumberAnnex, postCode, city,
        email, birthDate, bsn, userName, password);
    }
}
